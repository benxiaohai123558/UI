/*************************
 * 版权声明 **********************************
 * 版权所有：Copyright (c) 2008, 2013 黄晓峰
 * <p>
 * 工程名称：	zmsoft.ui.widget
 * 创建者：	huangxf 创建日期： 2013-11-14
 * 创建记录：	创建类结构。
 * <p>
 * ************************* 变更记录 ********************************
 * 修改者：
 * 修改日期：
 * 修改记录：
 * <p>
 * <p>
 * ......************************* To Do List*****************************
 * <p>
 * <p>
 * Suberversion 信息
 * ID:			$Id$
 * 源代码URL：	$HeadURL$
 * 最后修改者：	$LastChangedBy$
 * 最后修改日期：	$LastChangedDate$
 * 最新版本：		$LastChangedRevision$
 **/


package com.zmsoft.widget.listener;

/**
 * 文本回调.
 * @author <a href="mailto:rain999@gmail.com">黄晓峰</a>.
 * @version $Revision$.
 */
public interface ITextCallback {

    Short TYPE_NUM = 1, TYPE_WEIGHT = 2, TYPE_PRICE = 3, TYPE_REASON = 4;

    /**
     * 返回文本时动作.
     * @param text 返回的文本.
     */
    void onTextBack(String text, Short type);
}
