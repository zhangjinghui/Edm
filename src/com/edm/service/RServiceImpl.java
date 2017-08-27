package com.edm.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.rosuda.JRI.REXP;
import org.rosuda.JRI.Rengine;

import com.edm.dao.FileDAOImpl;
import com.edm.dao.IFileDAO;
import com.edm.r.R;
import com.edm.vo.User;

public class RServiceImpl implements IRService {
	private Rengine rEngine = R.getRengine();
	private IFileDAO dao = new FileDAOImpl();

	@Test
	public void randomForest(HttpServletRequest request,
			HttpServletResponse response) {
		String[] titleArr = null;

		String fileName = request.getParameter("fileName");
		// 森林大小
		String ntree = request.getParameter("ntree");
		if (ntree == null || ntree.equals("")) {
			ntree = "200";
		}

		// 决策树大小
		String nodesize = request.getParameter("nodesize");
		if (nodesize == null || nodesize.equals("")) {
			nodesize = "45";
		}
		// 种子
		String seed = request.getParameter("seed");
		if (seed == null || seed.equals("")) {
			seed = "100";
		}
		String userName = ((User) request.getSession().getAttribute("user"))
				.getUsername();
		String filePath = request.getSession().getServletContext()
				.getRealPath("\\").replace("\\", "/")
				+ "DataAnalysis/" + userName + "/" + fileName;

		try {
			// 1. 导入随机森林算法包
			rEngine.eval("library(randomForest)");

			// 2. 读文件到数据框
			rEngine.eval("edmdata <- read.csv('" + filePath + "',as.is = T)");

			// 3. 数据预处理
			rEngine.eval("edmdata <- na.omit(edmdata)");
			rEngine.eval("edmdata$是否补考[edmdata$补考数>0] <- 'yes'");
			rEngine.eval("edmdata$是否补考[edmdata$补考数==0] <- 'no'");
			rEngine.eval("edmdata$是否补考 <- factor(edmdata$是否补考)");
			rEngine.eval("edmdata$期末成绩 <- edmdata$学习成绩.80. <- edmdata$德育成绩 <- edmdata$综合测评 <- edmdata$专业排名 <- edmdata$补考数 <- edmdata$奖学金 <- NULL");

			rEngine.eval("title <- names(edmdata)");
			titleArr = rEngine.eval("title").asStringArray();
			List<String> titleList = new ArrayList<String>();
			for (int i = 0; i < titleArr.length; i++) {
				titleList.add(titleArr[i]);
			}
			request.setAttribute("titleList", titleList);
			// 4. 概要分析
			rEngine.eval("sumInfo <- summary(edmdata)");
			REXP rexp = rEngine.eval("sumInfo");

			String sumInfoArr[] = rexp.asStringArray();
			List<String> sumInfoList = new ArrayList<String>();
			for (int i = 0; i < sumInfoArr.length; i++) {
				sumInfoList.add(sumInfoArr[i]);
			}
			request.setAttribute("sumInfoList", sumInfoList);
			// 设置种子大小
			rEngine.eval("set.seed(" + seed + ")");

			// 5. 创建训练数据集和测试数据集
			rEngine.eval("train.subset <- sample(1:nrow(edmdata), nrow(edmdata),replace = T)");
			rEngine.eval("test.subset <- sample(1:nrow(edmdata), nrow(edmdata),replace = T)");

			// 6. 创建随机森林模型
			rEngine.eval("edmdata.rf  <- randomForest(是否补考~., data=edmdata[train.subset,], importance=TRUE, proximity=TRUE, ntree="
					+ ntree + ",nodesize=" + nodesize + ")");

			// 7. 在测试集上进行测试并比较结果
			rEngine.eval("pred <- predict(edmdata.rf, edmdata[test.subset,])");
			rEngine.eval("t=table(observed = edmdata[test.subset,]$是否补考, predicted = pred)");
			rEngine.eval("sensitivity=(t[1,1])/(t[1,1]+t[1,2])");
			rEngine.eval("specificity=(t[2,2])/(t[2,1]+t[2,2])");
			rEngine.eval("accuracy=(t[1,1]+t[2,2])/(t[1,1]+t[1,2]+t[2,1]+t[2,2])");

			// 敏感性
			REXP sensitivity = rEngine.eval("sensitivity");
			request.setAttribute("sensitivity", sensitivity.asDouble());

			// 特异性
			REXP specificity = rEngine.eval("specificity");
			request.setAttribute("specificity", specificity.asDouble());

			// 精确性
			REXP accuracy = rEngine.eval("accuracy");
			request.setAttribute("accuracy", accuracy.asDouble());

			rEngine.eval("importance <- t(round(importance(edmdata.rf),3))[3,]");
			REXP importance = rEngine.eval("importance");
			double[] importanceArr = importance.asDoubleArray();
			List<Double> importanceList = new ArrayList<Double>();
			for (int i = 0; i < importanceArr.length; i++) {
				importanceList.add(importanceArr[i]);
			}

			Integer id = Integer.parseInt(request.getParameter("id"));
			dao.analysis(id);

			request.setAttribute("importanceList", importanceList);
			request.getRequestDispatcher("/data/rfResult.jsp").forward(request,
					response);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			R.closeRengine();
		}
	}

	public void supportVectorMachine(HttpServletRequest request,
			HttpServletResponse response) {
		String[] titleArr = null;

		String fileName = request.getParameter("fileName");
		String degree = request.getParameter("degree");
		if (degree == null || degree.equals("")) {
			degree = "2";
		}
		String knots = request.getParameter("knots");
		if (knots == null || knots.equals("")) {
			knots = "3";
		}
		String userName = ((User) request.getSession().getAttribute("user"))
				.getUsername();
		String filePath = request.getSession().getServletContext()
				.getRealPath("\\").replace("\\", "/")
				+ "DataAnalysis/" + userName + "/" + fileName;

		try {
			// 1. 导入SVM算法包
			rEngine.eval("library(SVMMaj)");

			// 2. 读文件到数据框
			rEngine.eval("edmdata <- read.csv('" + filePath + "',as.is = T)");

			// 3. 数据预处理
			rEngine.eval("edmdata <- na.omit(edmdata)");
			rEngine.eval("edmdata$是否补考[edmdata$补考数>0] <- 'yes'");
			rEngine.eval("edmdata$是否补考[edmdata$补考数==0] <- 'no'");
			rEngine.eval("edmdata$是否补考 <- factor(edmdata$是否补考)");
			rEngine.eval("edmdata$期末成绩 <- edmdata$学习成绩.80. <- edmdata$德育成绩 <- edmdata$综合测评 <- edmdata$专业排名 <- edmdata$补考数 <- NULL");

			// 4. 获取字段名
			rEngine.eval("title <- names(edmdata)");
			titleArr = rEngine.eval("title").asStringArray();
			List<String> titleList = new ArrayList<String>();
			System.out.println(titleArr.length);
			for (int i = 0; i < titleArr.length; i++) {
				titleList.add(titleArr[i]);
			}
			request.setAttribute("titleList", titleList);

			// 5. 概要分析
			rEngine.eval("sumInfo <- summary(edmdata)");
			REXP rexp = rEngine.eval("sumInfo");

			String sumInfoArr[] = rexp.asStringArray();
			List<String> sumInfoList = new ArrayList<String>();
			for (int i = 0; i < sumInfoArr.length; i++) {
				sumInfoList.add(sumInfoArr[i]);
			}
			request.setAttribute("sumInfoList", sumInfoList);

			// 6. 创建训练数据集和测试数据集
			rEngine.eval("train.subset <- sample(1:nrow(edmdata), .8 * nrow(edmdata))");
			rEngine.eval("test.subset <- (1:nrow(edmdata))[-train.subset]");
			rEngine.eval("TB1<-subset(edmdata[train.subset, ],select=-是否补考)");
			rEngine.eval("TB2<-subset(edmdata[test.subset, ],select=-是否补考)");

			// 7. 在训练集上训练支持向量机模型degree:多项式核函数参数
			rEngine.eval("model <- svmmaj(TB1,edmdata[train.subset, ]$是否补考,spline.knots="
					+ knots + ",spline.degree=" + degree + ")");

			// 8. 在测试集上对训练模型进行测试,绘图并导出
			rEngine.eval("setwd('"
					+ request.getSession().getServletContext()
							.getRealPath("\\").replace("\\", "/")
					+ "DataAnalysis/" + userName + "')");

			rEngine.eval("future1=paste('svm','.jpg',sep='')");
			rEngine.eval("jpeg(file=future1)");
			rEngine.eval("pred<- predict(model,TB2,edmdata[test.subset, ]$是否补考,show.plot=TRUE)");
			rEngine.eval("dev.off()");

			// 输出测试结果
			rEngine.eval("svmResult <- print(pred)");

			REXP rexp1 = rEngine.eval("svmResult");
			double svmResult[] = rexp1.asDoubleArray();
			request.setAttribute("svmResult", svmResult);

			Integer id = Integer.parseInt(request.getParameter("id"));
			dao.analysis(id);

			request.getRequestDispatcher("/data/svmResult.jsp").forward(
					request, response);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			R.closeRengine();
		}
	}

	@Override
	public void decisionTree(HttpServletRequest request,
			HttpServletResponse response) {
		String[] titleArr = null;

		String fileName = request.getParameter("fileName");
		String userName = ((User) request.getSession().getAttribute("user"))
				.getUsername();
		String filePath = request.getSession().getServletContext()
				.getRealPath("\\").replace("\\", "/")
				+ "DataAnalysis/" + userName + "/" + fileName;

		try {
			// 1. 导入RWeka算法包(Decision Tree)
			rEngine.eval("library(RWeka)");

			// 2. 读文件到数据框
			rEngine.eval("edmdata <- read.csv('" + filePath + "',as.is = T)");

			// 3. 数据预处理
			rEngine.eval("edmdata <- na.omit(edmdata)");
			rEngine.eval("edmdata$是否补考[edmdata$补考数>0] <- 'yes'");
			rEngine.eval("edmdata$是否补考[edmdata$补考数==0] <- 'no'");
			rEngine.eval("edmdata$是否补考 <- factor(edmdata$是否补考)");
			rEngine.eval("edmdata$期末成绩 <- edmdata$学习成绩.80. <- edmdata$德育成绩 <- edmdata$综合测评 <- edmdata$专业排名 <- edmdata$补考数 <- edmdata$奖学金 <- NULL");

			rEngine.eval("title <- names(edmdata)");
			titleArr = rEngine.eval("title").asStringArray();
			List<String> titleList = new ArrayList<String>();
			for (int i = 0; i < titleArr.length; i++) {
				titleList.add(titleArr[i]);
			}
			request.setAttribute("titleList", titleList);
			// 4. 概要分析
			rEngine.eval("sumInfo <- summary(edmdata)");
			REXP rexp = rEngine.eval("sumInfo");

			String sumInfoArr[] = rexp.asStringArray();
			List<String> sumInfoList = new ArrayList<String>();
			for (int i = 0; i < sumInfoArr.length; i++) {
				sumInfoList.add(sumInfoArr[i]);
			}
			request.setAttribute("sumInfoList", sumInfoList);

			// 5. 创建训练数据集和测试数据集
			rEngine.eval("train.subset <- sample(1:nrow(edmdata), nrow(edmdata),replace = T)");
			rEngine.eval("test.subset <- sample(1:nrow(edmdata), nrow(edmdata),replace = T)");

			// 6. 在训练集上训练决策树模型
			rEngine.eval("m1<-J48(是否补考~.,data=edmdata[train.subset,])");

			// 7. 在测试集上进行测试并比较结果
			rEngine.eval("pred <- predict(m1, edmdata[test.subset,])");
			rEngine.eval("t=table(observed = edmdata[test.subset,'是否补考'], predicted = pred)");
			rEngine.eval("sensitivity=(t[1,1])/(t[1,1]+t[1,2])");
			rEngine.eval("specificity=(t[2,2])/(t[2,1]+t[2,2])");
			rEngine.eval("accuracy=(t[1,1]+t[2,2])/(t[1,1]+t[1,2]+t[2,1]+t[2,2])");

			// 敏感性
			REXP sensitivity = rEngine.eval("sensitivity");
			request.setAttribute("sensitivity", sensitivity.asDouble());

			// 特异性
			REXP specificity = rEngine.eval("specificity");
			request.setAttribute("specificity", specificity.asDouble());

			// 精确性
			REXP accuracy = rEngine.eval("accuracy");
			request.setAttribute("accuracy", accuracy.asDouble());

			Integer id = Integer.parseInt(request.getParameter("id"));
			dao.analysis(id);

			request.getRequestDispatcher("/data/dtResult.jsp").forward(request,
					response);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			R.closeRengine();
		}
	}

	@Override
	public void knn(HttpServletRequest request, HttpServletResponse response) {
		String[] titleArr = null;
		String k = request.getParameter("k");
		if (k == null || k.equals("")) {
			k = "3";
		}
		String fileName = request.getParameter("fileName");
		String userName = ((User) request.getSession().getAttribute("user"))
				.getUsername();
		String filePath = request.getSession().getServletContext()
				.getRealPath("\\").replace("\\", "/")
				+ "DataAnalysis/" + userName + "/" + fileName;

		try {
			// 1. 导入class包
			rEngine.eval("library(class)");

			// 2. 读文件到数据框
			rEngine.eval("edmdata <- read.csv('" + filePath + "',as.is = T)");

			// 3. 数据预处理
			rEngine.eval("edmdata <- na.omit(edmdata)");
			rEngine.eval("edmdata$是否补考[edmdata$补考数>0] <- 'yes'");
			rEngine.eval("edmdata$是否补考[edmdata$补考数==0] <- 'no'");
			rEngine.eval("edmdata$是否补考 <- factor(edmdata$是否补考)");
			rEngine.eval("edmdata$期末成绩 <- edmdata$学习成绩.80. <- edmdata$德育成绩 <- edmdata$综合测评 <- edmdata$专业排名 <- edmdata$补考数 <- edmdata$奖学金 <- NULL");

			// 4. 获取字段名
			rEngine.eval("title <- names(edmdata)");
			titleArr = rEngine.eval("title").asStringArray();
			List<String> titleList = new ArrayList<String>();
			for (int i = 0; i < titleArr.length; i++) {
				titleList.add(titleArr[i]);
			}
			request.setAttribute("titleList", titleList);

			// 5. 概要分析
			rEngine.eval("sumInfo <- summary(edmdata)");
			REXP rexp = rEngine.eval("sumInfo");

			String sumInfoArr[] = rexp.asStringArray();
			List<String> sumInfoList = new ArrayList<String>();
			for (int i = 0; i < sumInfoArr.length; i++) {
				sumInfoList.add(sumInfoArr[i]);
			}
			request.setAttribute("sumInfoList", sumInfoList);

			// 6. 创建训练数据集和测试数据集
			rEngine.eval("train.subset <- sample(1:nrow(edmdata), nrow(edmdata),replace = T)");
			rEngine.eval("test.subset <- sample(1:nrow(edmdata), nrow(edmdata),replace = T)");
			rEngine.eval("TB1<-subset(edmdata[train.subset, ],select=-是否补考)");
			rEngine.eval("TB2<-subset(edmdata[test.subset, ],select=-是否补考)");

			// 7. 在训练集上训练knn模型，并输出
			rEngine.eval("pred<-knn(TB1,TB2,edmdata[train.subset, ]$是否补考,k="
					+ k + ")");
			rEngine.eval("print(pred)");

			// 8. 比较实际类型和预测类型,并计算分类正确率
			rEngine.eval("t=table(pred,edmdata[test.subset, ]$是否补考)");
			rEngine.eval("sensitivity=(t[1,1])/(t[1,1]+t[1,2])");
			rEngine.eval("specificity=(t[2,2])/(t[2,1]+t[2,2])");
			rEngine.eval("accuracy=(t[1,1]+t[2,2])/(nrow(edmdata[test.subset,]))");

			// 敏感性
			REXP sensitivity = rEngine.eval("sensitivity");
			request.setAttribute("sensitivity", sensitivity.asDouble());

			// 特异性
			REXP specificity = rEngine.eval("specificity");
			request.setAttribute("specificity", specificity.asDouble());

			// 精确性
			REXP accuracy = rEngine.eval("accuracy");
			request.setAttribute("accuracy", accuracy.asDouble());

			Integer id = Integer.parseInt(request.getParameter("id"));
			dao.analysis(id);

			request.getRequestDispatcher("/data/knnResult.jsp").forward(
					request, response);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			R.closeRengine();
		}
	}

	@Override
	public void mars(HttpServletRequest request, HttpServletResponse response) {
		String[] titleArr = null;

		String fileName = request.getParameter("fileName");
		String userName = ((User) request.getSession().getAttribute("user"))
				.getUsername();
		String filePath = request.getSession().getServletContext()
				.getRealPath("\\").replace("\\", "/")
				+ "DataAnalysis/" + userName + "/" + fileName;

		try {
			// 1. 导入earth包
			rEngine.eval("library(earth)");

			// 2. 读文件到数据框
			rEngine.eval("edmdata <- read.csv('" + filePath + "',as.is = T)");

			// 3. 数据预处理
			rEngine.eval("edmdata <- na.omit(edmdata)");
			rEngine.eval("edmdata$是否补考[edmdata$补考数>0] <- 'yes'");
			rEngine.eval("edmdata$是否补考[edmdata$补考数==0] <- 'no'");
			rEngine.eval("edmdata$是否补考 <- factor(edmdata$是否补考)");
			rEngine.eval("edmdata$期末成绩 <- edmdata$学习成绩.80. <- edmdata$德育成绩 <- edmdata$综合测评 <- edmdata$专业排名 <- edmdata$补考数 <- edmdata$奖学金 <- NULL");

			// 4. 获取字段名
			rEngine.eval("title <- names(edmdata)");
			titleArr = rEngine.eval("title").asStringArray();
			List<String> titleList = new ArrayList<String>();
			for (int i = 0; i < titleArr.length; i++) {
				titleList.add(titleArr[i]);
			}
			request.setAttribute("titleList", titleList);

			// 5. 概要分析
			rEngine.eval("sumInfo <- summary(edmdata)");
			REXP rexp = rEngine.eval("sumInfo");

			String sumInfoArr[] = rexp.asStringArray();
			List<String> sumInfoList = new ArrayList<String>();
			for (int i = 0; i < sumInfoArr.length; i++) {
				sumInfoList.add(sumInfoArr[i]);
			}
			request.setAttribute("sumInfoList", sumInfoList);

			// 6. 创建训练数据集和测试数据集
			rEngine.eval("train.subset <- sample(1:nrow(edmdata), nrow(edmdata),replace = T)");
			rEngine.eval("test.subset <- sample(1:nrow(edmdata), nrow(edmdata),replace = T)");

			// 7. 在训练集上训练mars模型，并输出
			rEngine.eval("a <- earth(是否补考~., data = edmdata[train.subset, ],glm=list(family=binomial))");
			rEngine.eval("pred <- predict(a, newdata = edmdata[test.subset, ],thresh=.5,type='class')");
			rEngine.eval("y <- edmdata$是否补考[test.subset]");

			// 8. 比较实际类型和预测类型,并计算分类正确率
			rEngine.eval("t=table(pred,edmdata[test.subset, ]$是否补考)");
			rEngine.eval("sensitivity=(t[1,1])/(t[1,1]+t[1,2])");
			rEngine.eval("specificity=(t[2,2])/(t[2,1]+t[2,2])");
			rEngine.eval("accuracy=(t[1,1]+t[2,2])/(nrow(edmdata[test.subset,]))");

			// 敏感性
			REXP sensitivity = rEngine.eval("sensitivity");
			request.setAttribute("sensitivity", sensitivity.asDouble());

			// 特异性
			REXP specificity = rEngine.eval("specificity");
			request.setAttribute("specificity", specificity.asDouble());

			// 精确性
			REXP accuracy = rEngine.eval("accuracy");
			request.setAttribute("accuracy", accuracy.asDouble());

			Integer id = Integer.parseInt(request.getParameter("id"));
			dao.analysis(id);

			request.getRequestDispatcher("/data/marsResult.jsp").forward(
					request, response);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			R.closeRengine();
		}
	}

}