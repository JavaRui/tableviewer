package demo.order;

import com.crwu.swt.tableviewer.annotation.TableColumnSetting;

/**
 * @author cr.wu
 *
 * 2015-8-2
 */

public class InOrderDesc {

    @TableColumnSetting(index = 0,columnText = "这是商品")
	private int inOrderId;
    @TableColumnSetting(index = 1,columnText = "编号")
	private String no;
    @TableColumnSetting(index = 2,columnText = "spname")
	private String supName;
    @TableColumnSetting(index = 3,columnText = "总金额")
	private float allMoney = 0.00f;
	
	public int getInOrderId() {
		return inOrderId;
	}
	public void setInOrderId(int inOrderId) {
		this.inOrderId = inOrderId;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getSupName() {
		return supName;
	}
	public void setSupName(String supName) {
		this.supName = supName;
	}
	public float getAllMoney() {
		return allMoney;
	}
	public void setAllMoney(float allMoney) {
		this.allMoney = allMoney;
	}
	@Override
	public String toString() {
		return "InOrderDesc [inOrderId=" + inOrderId + ", no=" + no
				+ ", supName=" + supName + ", allMoney=" + allMoney + "]";
	}

	public String getFlag(){
		if(allMoney>20){
			return "是";
		}
		return "否";
	}
	
	
}

