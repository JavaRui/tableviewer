package demo.order;

import com.crwu.swt.tableviewer.annotation.TableColumnSetting;
import lombok.Data;

/**
 * @author cr.wu
 *
 * 2015-8-2
 */
@Data
public class InOrderDesc {

    @TableColumnSetting(index = 0,columnText = "这是商品")
	private int inOrderId;
    @TableColumnSetting(index = 1,columnText = "编号")
	private String no;
    @TableColumnSetting(index = 2,columnText = "spname")
	private String supName;
    @TableColumnSetting(index = 3,columnText = "总金额")
	private float allMoney = 0.00f;


	public String getFlag(){
		if(allMoney>20){
			return "是";
		}
		return "否";
	}
	
	
}

