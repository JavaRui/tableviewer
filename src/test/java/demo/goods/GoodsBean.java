package demo.goods;

import com.crwu.swt.tableviewer.annotation.TableColumnSetting;

/**
 * @author cr.wu
 *
 */
public class GoodsBean {
	

    @TableColumnSetting(index = 0,columnText = "这是商品")
	private int goodsId;

    @TableColumnSetting(index = 1,columnText = "name",modify = true)
    private String goodsName;

    @TableColumnSetting(index = 2,columnText = "规格",modify = true)
    private String stardard;
    @TableColumnSetting(index = 3,columnText = "类型",modify = true)
    private String category;
	
	
	public int getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getStardard() {
		return stardard;
	}
	public void setStardard(String stardard) {
		this.stardard = stardard;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	@Override
	public String toString() {
		return "GoodsBean [goodsId=" + goodsId + ", goodsName=" + goodsName + ", stardard=" + stardard  + ", category=" + category + "]";
	}
	
	
	
}

