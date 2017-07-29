package com.spw.elife.staff.util;

public class PrepareConstant {
	/*********************************************************** 新筹start ***************************************************************/
	/**
	 * 分部数
	 */
	public final static int[] DEPART_COUNT = {6, 12 , 20};
	
	/**
	 * 首月人力
	 */
	public final static int[] MONTH_FIRST_PERSON = {18, 27 , 36, 45, 54};
	
	/**
	 * 首月保费(万元) 6, 9, 12, 15, 18
	 */
	public final static double[] MONTH_FIRST_INSURANCE = {60000, 90000 , 120000, 150000, 180000};
	
	/**
	 * 次月人力
	 */
	public final static int[] MONTH_TWO_PERSON = {36, 54 , 72, 90, 108};
	
	/**
	 * 次月保费(万元)15, 18, 24, 30, 36
	 */
	public final static double[] MONTH_TWO_INSURANCE = {150000, 180000 ,240000, 300000, 360000};
	
	/**
	 * 末月人力
	 */
	public final static int[] MONTH_THREE_PERSON = {60, 90 , 120, 150, 180};
	
	
	/**
	 * 末月保费(万元)20, 30, 40, 50, 60
	 */
	public final static double[] MONTH_THREE_INSURANCE = {200000, 300000 , 400000, 500000, 600000};
	
	/**
	 * 新筹督训长基本工资0, 8000, 10000 , 12000, 15000, 18000
	 */
	public final static int[] BASIC_SALARY = {0, 8000, 10000 , 12000, 15000, 18000};
	
	/**
	 * 新筹督训长筹备期间经营费用比率
	 */
	public final static double MANAGE_RATE = 0.015;
	
	/**
	 * 新筹督训长基本工资发放比率 价保区间 80%≤ x <100%
	 */
	public final static double BASIC_RATE = 0.8;
	
	/**
	 * 新筹督训长测评期基本工资发放比率
	 */
	public final static double BASIC_CEPING_RATE = 0.7;
	
	/**
	 * 新筹督训区筹备期间费用补贴 2000元
	 */
	public final static double COST_SUBSIDY = 2000;
	
	/**
	 * 新筹督训区筹备期间定额补贴 1500元
	 */
	public final static double SUBSIDY_RECEIPT = 1500;
	
	/**
	 * 测评期达标 1个分部4个分部成员，4万保费
	 */
	public final static int[] MONTH_ACHIEVE = {1, 4 , 40000};
	
	/**
	 * 测评期达标合格奖励 0, 8000, 10000 , 12000, 15000, 18000
	 */
	public final static double[] MONTH_REWARD = {0, 8000, 10000 , 12000, 15000, 18000};
	
	/*********************************************************** 新筹end ***************************************************************/
	
	/**
	 * 督训长工资价保区间（万元） 20, 30, 40, 50, 60, 70, 80, 90, 100
	 */
	public final static double[] SALARY_JIABAO_INSURANCE = {200000, 300000 , 400000, 500000, 600000, 700000, 800000, 900000, 1000000};
	
	/**
	 * 督训长工资区间 4000, 8000, 10000 , 12000, 14000, 16000, 18000, 20000, 22000, 24000
	 */
	public final static double[] SALARY_INSURANCE = {4000, 8000, 10000 , 12000, 14000, 16000, 18000, 20000, 22000, 24000};
	
	/**
	 * 分部达标 1万 2.4万 4万保费
	 */
	public final static double[] MONTH_INSURANCE = {10000, 24000, 40000};
	
	/**
	 * 分部达标月奖 
	 * 有效分部100/个 分部业绩1万
	 * 合格分部300/个 分部业绩2.4万
	 * 绩优分部500/个 分部业绩4万
	 * 
	 */
	public final static double[] MONTH_MONEY_INSURANCE = {100, 300, 500};
	
	/**
	 * 绩效工资提成 
	 */
	public final static double MONTH_PERFORMANCE = 0.01;
	
	/**
	 * 绩效工资提成 分割点
	 */
	public final static double PERFORMANCE_SPLIT = 100000;

	/**
	 * 正式期 入观察期分界点 60万元
	 */
	public final static double PRICE_POINT = 600000;
	
	/**
	 * 正式期 绩效工资与分部月奖当月发放60%，年度发放40%
	 */
	public final static double MONTH_RATE = 0.6;
	
	/**
	 * 等级0-10
	 */
	public final static String[] GRADE = {"0", "1" , "2", "3", "4", "5", "6", "7", "8", "9", "10"};
	
	/**
	 * 常量 , char
	 */
	public final static String CONSTANT_SPLIT = ",";
	
	/**
	 * 常量 ; char
	 */
	public final static String CONSTANT_SEMICOLON = ";";
	
	/**
	 * 常量 @ char
	 */
	public final static String CONSTANT_AT = "@";

	/**
	 * 常量 0 char
	 */
	public final static String CONSTANT_ZERO = "0";
	
	/**
	 * 常量 1 char
	 */
	public final static String CONSTANT_ONE = "1";
	
	/**
	 * 常量 2 char
	 */
	public final static String CONSTANT_TWO = "2";
	
	/**
	 * 常量 3 char
	 */
	public final static String CONSTANT_THREE = "3";
	
	/**
	 * 获取等级名称
	 * 
	 * @param standardGrade
	 * @return
	 */
	public static String getStandard(String standardGrade) {
		if (standardGrade == null) {
			return "";
		}
		String[] grades = standardGrade.split(CONSTANT_SPLIT);
		String name = "未达标";
		if (grades.length == 3) {
			if (CONSTANT_ONE.equals(grades[0])) {
				switch (grades[1]) {
				case "1":
					name = "第一档";
					break;
				case "2":
					name = "第二档";
					break;
				case "3":
					name = "第三档";
					break;
				case "4":
					name = "第四档";
					break;
				case "5":
					name = "第五档";
					break;
				case "6":
					name = "第六档";
					break;
				case "7":
					name = "第七档";
					break;
				case "8":
					name = "第八档";
					break;
				case "9":
					name = "第九档";
					break;
				default:
					break;
				}
			}
		}
		return name;
	}
	
	/**
	 * 获取筹备节奏名称
	 * 
	 * @param standardGrade
	 * @return
	 */
	public static String getPrepareStepName(String prepareStep, String type) {
		String name = "";
		if (prepareStep == null || type == null) {
			return name;
		}
		switch (type) {
		case CONSTANT_ZERO:// 正式期
		case CONSTANT_ONE:// 筹备期
			switch (prepareStep) {
			case CONSTANT_ONE:// 首月
				name = "首月";
				break;
			case CONSTANT_TWO:// 次月
				name = "次月";
				
				break;
			case CONSTANT_THREE:// 末月
				name = "末月";
				break;

			default:
				break;
			}
			break;
		case CONSTANT_TWO:// 测评期
			switch (prepareStep) {
			case CONSTANT_ONE:// 首月
				name = "测评期首月";
				break;
			case CONSTANT_TWO:// 次月
				name = "测评期次月";
				
				break;
			case CONSTANT_THREE:// 末月
				name = "测评期末月";
				break;

			default:
				break;
			}
			break;
		case CONSTANT_THREE:// 观察期
			
			break;

		default:
			break;
		}
		return name;
	}
	
	/**
	 * 获取状态名称
	 * 
	 * @param standardGrade
	 * @return
	 */
	public static String getStateName(String stateFlag) {
		String name = "筹备中";
		if (stateFlag == null) {
			return name;
		}
		switch (stateFlag) {
		case "0":
			name = "正式督训长";
			break;
		case "1":
			name = "筹备中";
			break;
		case "2":
			name = "筹备成功";
			break;
		case "3":
			name = "筹备失败";
			break;
		default:
			break;
		}
		return name;
	}
	
	/**
	 * 获取期名名称
	 * 
	 * @param type
	 * @return
	 */
	public static String getTypeName(String type) {
		String name = "筹备中";
		if (type == null) {
			return name;
		}
		switch (type) {
		case "0":
			name = "正式期";
			break;
		case "1":
			name = "筹备期";
			break;
		case "2":
			name = "测评期";
			break;
		case "3":
			name = "观察期";
			break;
		default:
			break;
		}
		return name;
	}
}
