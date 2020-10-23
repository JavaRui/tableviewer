package demo.goods;

import com.crwu.swt.tableviewer.annotation.TableColumnSetting;
import lombok.Data;

/**
 * @author cr.wu
 *
 */
@Data
public class GoodsBean {
	

    @TableColumnSetting(index = 0,columnText = "这是商品")
	private int goodsId;

    @TableColumnSetting(index = 1,columnText = "name",modify = true)
    private String goodsName;

    @TableColumnSetting(index = 2,columnText = "规格",modify = true)
    private String stardard;
    @TableColumnSetting(index = 3,columnText = "类型",modify = true)
    private String category;

	
}

