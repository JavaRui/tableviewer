package demo;

import com.crwu.swt.common.INBack;
import com.crwu.swt.common.base.YtComposite;
import com.crwu.swt.common.util.RandomUtil;
import com.crwu.swt.common.util.SwtVoid;
import com.crwu.swt.tableviewer.product.YtCheckBoxTable;
import demo.goods.*;
import demo.order.*;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import java.util.ArrayList;
import java.util.List;


public class DemoMain extends YtComposite {

	private YtCheckBoxTable goodsBeanTable;
	private List<String> tagList = new ArrayList<String>();
	
	/**  **/
	public DemoMain(Composite parent) {
		super(parent);
		createContents();
	}

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		SwtVoid.createSwt(new INBack() {
			
			@Override
			public void callBack(Object o) {
				new DemoMain((Composite) o);
			}
		});
	}

	private RandomUtil r = RandomUtil.getInst();


/***
 * 1.通过model的属性，排列出，属性的顺序，且用|隔开
 * 如GoodsBean : column_index = goodsId|goodName|stardard
 * 
 * 2.设置表头的文字，此时文字应该与属性对应，且用|隔开
 * 如GoodsBean : column_name = 商品id|商品名称|商品规格
 * 
 * 3.设置可编辑的表头index，对应文字的下标
 * 如在GoodsBean中，我想设置 商品名称和商品规格可以编辑，GoodsBean : can_modify = 1|2
 * 
 * 4.调用PropertyData.put方法，把GoodsBean.class对应以上的设置
 * PropertyData.put(GoodsBean.class,"订单id|合同号|供应商|总金额", "inOrderId|no|supName|allMoney","1|2");
 * 
 * 5.设置tableViewer为默认排序，可编辑，且具有自适应宽度，多行等功能
 * TableUtil.setCommon(tableViewer, GoodsBean.class);
 * 
 * */
	protected void createContents() {
		
		setGridLayout();

		initTable1();
		initTable2();
		
	}
	
	/**
	 * 演示table的增删改
	 */
	private void initTable1() {
		
		YtComposite btnComposite = new YtComposite(this , SWT.BORDER);
		
		Button addBtn = new Button( btnComposite , SWT.PUSH);
		addBtn.setText("添加一个条目");
		addBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				super.widgetSelected(e);
				goodsBeanTable.add(getRandomGoods(r.getInt(100)));
			}
		});
		
		Button delBtn = new Button(btnComposite, SWT.PUSH);
		delBtn.setText("删除所选");
		delBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				super.widgetSelected(e);
				List<GoodsBean> list = (List<GoodsBean>) goodsBeanTable.getCheck();
				for(int i = 0 ; i < list.size() ; i ++ ){
					String index = list.get(i).getGoodsName();
					tagList.remove(index);
				}
				lll(tagList);
				goodsBeanTable.delCheck();
			}
		});
		
		Button clearBtn = new Button(btnComposite, SWT.PUSH);
		clearBtn.setText("清除所有");
		clearBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				super.widgetSelected(e);
				goodsBeanTable.clear();
			}
		});
		
		Button getCheckBtn = new Button(btnComposite, SWT.PUSH);
		getCheckBtn.setText("获取所选的条目");
		getCheckBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				super.widgetSelected(e);
				List list = goodsBeanTable.getCheck();
				lll(list);
			}
		});
		
		Button replaceBtn = new Button(btnComposite, SWT.PUSH);
		replaceBtn.setText("根据属性值替换条目");
		replaceBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String goodsName = getRandomTag();
				tagList.remove(goodsName);
				lll("替换一个goodsName为：   "+goodsName+"   的条目");
				goodsBeanTable.replaceObjByField(goodsName, getRandomGoods(r.getInt(100)), "goodsName");
			}
		});
		
		Button replaceBtn2 = new Button(btnComposite, SWT.PUSH);
		replaceBtn2.setText("根据下标值替换条目");
		replaceBtn2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String goodsName = getRandomTag();
				tagList.remove(goodsName);
				lll("替换一个下标在第 1 行,属性值为：   "+goodsName+"   的条目");
//				goodsBeanTable.replaceObjByColumnIndex(goodsName, getRandomGoods(r.getInt(100)), 1);
			}
		});
		
		
		btnComposite.setGridLayoutByChildren(false);
		
		List<GoodsBean> list = new ArrayList<GoodsBean>();
		
		for(int i = 0 ; i < 10 ; i++){
			GoodsBean gb = getRandomGoods(i);
			list.add(gb);
		}
		
		goodsBeanTable = YtCheckBoxTable.newCheckBox(this, GoodsBean.class);
		goodsBeanTable.setEoList(list);
		goodsBeanTable.setSelectBack(new INBack() {
			
			@Override
			public void callBack(Object o) {
				lll("选择了：    "+o.toString());
			}
		});
		goodsBeanTable.setGd(true, true);
	}
	
	private GoodsBean getRandomGoods(int id){
		GoodsBean gb = new GoodsBean();
		gb.setGoodsId(id);
		gb.setGoodsName(r.getRandomECN(0, 5));
		tagList.add(gb.getGoodsName());
		gb.setStardard(r.getRandomECN(0, 5));
		gb.setCategory("类目");
		return gb;
	}
	
	private String getRandomTag(){
		return tagList.get(r.getInt(tagList.size()));
	}
	
	
	/**
	 * 表头的扩展写法
	 */
	private void initTable2() {
		List<InOrderDesc> list = new ArrayList<InOrderDesc>();
		
		for(int i = 0 ; i < 10 ; i++){
			InOrderDesc gb = new InOrderDesc();
			gb.setInOrderId(i);
			gb.setNo(r.getEnRandom(5, 10));
			gb.setSupName(r.getChRandom(1, 3));
			gb.setAllMoney(Float.valueOf(r.getNumFixed(4))/100);
			list.add(gb);
		}
		
		YtCheckBoxTable tableViewer = YtCheckBoxTable.newSimpleTable(this, InOrderDesc.class);
		tableViewer.setEoList(list);
		tableViewer.setGd(true, true);
		
		
	}
	
	

}
