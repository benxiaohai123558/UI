/************************* 版权声明 **********************************
 * 版权所有：Copyright (c) 2008, 2013 黄晓峰
 *
 * 工程名称：	zmsoft.ui.widget
 * 创建者：	huangxf 创建日期： 2013-12-16
 * 创建记录：	创建类结构。
 *
 * ************************* 变更记录 ********************************
 * 修改者： 
 * 修改日期：
 * 修改记录：
 *
 * 
 * ......************************* To Do List*****************************
 * 
 *
 * Suberversion 信息
 * ID:			$Id$
 * 源代码URL：	$HeadURL$
 * 最后修改者：	$LastChangedBy$
 * 最后修改日期：	$LastChangedDate$
 * 最新版本：		$LastChangedRevision$
 **/


package com.zmsoft.widget;

/**
 * 输入类型.
 *
 * @author <a href="mailto:157195005@qq.com">陈杨</a>.
 * @version $
 * @create 2014年6月17日 上午10:15:08
 */
public interface ICommonItemType {
    /**
     * <code>浮点型</code>
     */
	int T_FLOAT = 1;
    /**
     * <code>整型</code>
     */
	int T_INT = 2;
    /**
     * <code>时间格式</code>
     */
	int DATE = 3;
    /**
     * <code>文本</code>
     */
	int STRING = 4;
    /**
     * <code>ip地址</code>
     */
	int IP = 5;
    /**
     * <code>小时分</code>
     */
	int Time = 6;
    /**
     * <code>日期+小时分</code>
     */
	int DATE_TIME = 7;
    /**
     * <code>只是为了展示用</code>
     */
	int SHOW_ONLY = 8;
    /**
     * <code>邮件</code>
     */
	int MAIL = 9;
    /**
     * <code>非跳框小键盘直接item出来一个小键盘</code>
     */
	int NUM_KEYBOARD = 10;
    /**
     * <code>数字滚轮</code>
     */
	int NUM_WHEEL = 11;
    /**
     * <code>开关按钮</code>
     */
    int BOOL = 12;
}
