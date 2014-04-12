package gui;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;

public class MainWindow {

	protected Shell shell;
	private Table table;
	private Table table_1;
	private Text searchText;
	private Table table_2;
	private Text quickSearchText;
	private List <CTabItem> selectedSW_tabItemList;
	private Text textTotalSum;
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MainWindow window = new MainWindow();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		final List<CTabItem> selectedSW_tabItemList = new ArrayList<CTabItem>();
		shell = new Shell();
		shell.setSize(700, 550);
		shell.setText("Клиентская программа интернет-магазина программного обеспечения");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Composite composite = new Composite(shell, SWT.NONE);
		GridLayout gl_composite = new GridLayout(4, false);
		composite.setLayout(gl_composite);
		
		final Tree tree = new Tree(composite, SWT.BORDER);

		GridData gd_tree = new GridData(SWT.FILL, SWT.FILL, false, true, 1, 2);
		gd_tree.widthHint = 120;
		tree.setLayoutData(gd_tree);
		
		TreeColumn treeColumn = new TreeColumn(tree, SWT.LEFT);
		treeColumn.setWidth(200);
		treeColumn.setText("\u0413\u043B\u0430\u0432\u043D\u0430\u044F");
		
		TreeItem treeItem_Home = new TreeItem(tree, SWT.NONE);
		treeItem_Home.setText(new String[] {});
		treeItem_Home.setText("\u0413\u043B\u0430\u0432\u043D\u0430\u044F");
		treeItem_Home.setExpanded(true);
		
		TreeItem treeItem_Korzina = new TreeItem(tree, SWT.NONE);
		treeItem_Korzina.setText("\u041A\u043E\u0440\u0437\u0438\u043D\u0430");
		
		TreeItem treeItem_Search = new TreeItem(tree, SWT.NONE);
		treeItem_Search.setText("\u041F\u043E\u0438\u0441\u043A");
		
		TreeItem treeItem_All_SW = new TreeItem(tree, SWT.NONE);
		treeItem_All_SW.setText("\u0412\u0441\u0435 \u043F\u0440\u043E\u0433\u0440\u0430\u043C\u043C\u044B");
		
		TreeItem treeItem_categories = new TreeItem(tree, SWT.NONE);
		treeItem_categories.setText("\u041F\u043E \u043A\u0430\u0442\u0435\u0433\u043E\u0440\u0438\u044F\u043C");
		
		TreeItem treeItem_5 = new TreeItem(treeItem_categories, SWT.NONE);
		treeItem_5.setText("\u0410\u043D\u0442\u0438\u0432\u0438\u0440\u0443\u0441\u044B");
		
		TreeItem treeItem_6 = new TreeItem(treeItem_categories, SWT.NONE);
		treeItem_6.setText("\u041E\u0444\u0438\u0441");
		
		TreeItem treeItem_7 = new TreeItem(treeItem_categories, SWT.NONE);
		treeItem_7.setText("\u041E\u043F\u0435\u0440\u0430\u0446\u0438\u043E\u043D\u043D\u044B\u0435 \u0441\u0438\u0441\u0442\u0435\u043C\u044B");
		
		TreeItem treeItem_8 = new TreeItem(treeItem_categories, SWT.NONE);
		treeItem_8.setText("\u041A\u0443\u0447\u0430 \u0434\u0440\u0443\u0433\u0438\u0445 \u043A\u0430\u0442\u0435\u0433\u043E\u0440\u0438\u0439");
		treeItem_8.setExpanded(true);
		treeItem_categories.setExpanded(true);
		new Label(composite, SWT.NONE);
		
		quickSearchText = new Text(composite, SWT.BORDER);
		quickSearchText.setToolTipText("\u0411\u044B\u0441\u0442\u0440\u044B\u0439 \u043F\u043E\u0438\u0441\u043A");
		GridData gd_quickSearchText = new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1);
		gd_quickSearchText.widthHint = 140;
		quickSearchText.setLayoutData(gd_quickSearchText);
		
		Button quickSearchBtn = new Button(composite, SWT.NONE);
		quickSearchBtn.setImage(SWTResourceManager.getImage(MainWindow.class, "/gui/find2_16.png"));
		GridData gd_quickSearchBtn = new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1);
		gd_quickSearchBtn.heightHint = 20;
		gd_quickSearchBtn.widthHint = 25;
		quickSearchBtn.setLayoutData(gd_quickSearchBtn);
		//quickSearchBtn.setImage(new Image())
		
		final CTabFolder tabFolder = new CTabFolder(composite, SWT.BORDER);
		GridData gd_tabFolder = new GridData(SWT.FILL, SWT.FILL, true, true, 3, 1);
		gd_tabFolder.widthHint = 469;
		gd_tabFolder.heightHint = 408;
		tabFolder.setLayoutData(gd_tabFolder);
		tabFolder.setSelectionBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		
		final CTabItem tabItem1_Home = new CTabItem(tabFolder, SWT.NONE);
		tabItem1_Home.setText("\u0413\u043B\u0430\u0432\u043D\u0430\u044F");
		
		Composite composite_1 = new Composite(tabFolder, SWT.BORDER);
		tabItem1_Home.setControl(composite_1);
		composite_1.setLayout(new GridLayout(1, false));
		
		Label label = new Label(composite_1, SWT.NONE);
		label.setText("\u041F\u0440\u0438\u0432\u0435\u0442\u0441\u0442\u0432\u0438\u0435");
		
		final CTabItem tabItem2_SWList = new CTabItem(tabFolder, SWT.NONE);
		tabItem2_SWList.setText("\u0421\u043F\u0438\u0441\u043E\u043A \u043F\u0440\u043E\u0433\u0440\u0430\u043C\u043C");
		
		Composite composite_2 = new Composite(tabFolder, SWT.NONE);
		tabItem2_SWList.setControl(composite_2);
		composite_2.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		table = new Table(composite_2, SWT.BORDER | SWT.FULL_SELECTION);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(150);
		tableColumn.setText("\u041D\u0430\u0437\u0432\u0430\u043D\u0438\u0435");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(90);
		tableColumn_1.setText("\u0420\u0430\u0437\u0440\u0430\u0431\u043E\u0442\u0447\u0438\u043A");
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(80);
		tableColumn_2.setText("\u0426\u0435\u043D\u0430");
		
		TableItem tableItem = new TableItem(table, SWT.NONE);
		tableItem.setText(new String[] {"Microsoft office 2010","Microsoft","1000"});
		
		TableItem tableItem_1 = new TableItem(table, SWT.NONE);
		tableItem_1.setText("Антивирус Касперского");
		
		TableItem tableItem_2 = new TableItem(table, SWT.NONE);
		tableItem_2.setText(new String[] {"ESET NOD32 6.0","ESET",""});
		
		TableItem tableItem_3 = new TableItem(table, SWT.NONE);
		tableItem_3.setText(new String[] {"Windows 7 x64 Ultimate Edition","Microsoft",""});
		
		final CTabItem tabItem3_Korzina = new CTabItem(tabFolder, SWT.NONE);
		tabItem3_Korzina.setText("\u041A\u043E\u0440\u0437\u0438\u043D\u0430");
		
		Composite composite_3 = new Composite(tabFolder, SWT.NONE);
		tabItem3_Korzina.setControl(composite_3);
		composite_3.setLayout(new GridLayout(2, false));
		
		Label label_1 = new Label(composite_3, SWT.NONE);
		label_1.setText("\u041F\u043E\u043B\u044C\u0437\u043E\u0432\u0430\u0442\u0435\u043B\u044C:");
		new Label(composite_3, SWT.NONE);
		
		Label label_3 = new Label(composite_3, SWT.NONE);
		label_3.setText("\u0418\u043C\u044F:");
		
		Label label_5 = new Label(composite_3, SWT.NONE);
		label_5.setText("\u0438\u043C\u044F");
		
		Label label_4 = new Label(composite_3, SWT.NONE);
		label_4.setText("\u0424\u0430\u043C\u0438\u043B\u0438\u044F:");
		
		Label lblNewLabel = new Label(composite_3, SWT.NONE);
		lblNewLabel.setText("\u0444\u0430\u043C\u0438\u043B\u0438\u044F");
		
		Label label_6 = new Label(composite_3, SWT.NONE);
		label_6.setText("\u041D\u043E\u043C\u0435\u0440 \u043A\u0430\u0440\u0442\u044B:");
		
		Label label_7 = new Label(composite_3, SWT.NONE);
		label_7.setText("\u043D\u043E\u043C\u0435\u0440");
		
		Label label_2 = new Label(composite_3, SWT.NONE);
		label_2.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		label_2.setText("\u0421\u043F\u0438\u0441\u043E\u043A \u043F\u043E\u043A\u0443\u043F\u043E\u043A:");
		
		table_1 = new Table(composite_3, SWT.BORDER | SWT.FULL_SELECTION);
		table_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		table_1.setHeaderVisible(true);
		table_1.setLinesVisible(true);
		
		TableColumn tableColumn_3 = new TableColumn(table_1, SWT.NONE);
		tableColumn_3.setWidth(200);
		tableColumn_3.setText("\u041D\u0430\u0437\u0432\u0430\u043D\u0438\u0435");
		
		TableColumn tableColumn_5 = new TableColumn(table_1, SWT.NONE);
		tableColumn_5.setWidth(55);
		tableColumn_5.setText("\u041A\u043E\u043B-\u0432\u043E");
		
		TableColumn tableColumn_4 = new TableColumn(table_1, SWT.NONE);
		tableColumn_4.setWidth(100);
		tableColumn_4.setText("\u0426\u0435\u043D\u0430");
		
		Label label_8 = new Label(composite_3, SWT.NONE);
		label_8.setText("\u0418\u0442\u043E\u0433\u043E\u0432\u0430\u044F \u0441\u0443\u043C\u043C\u0430:");
		
		textTotalSum = new Text(composite_3, SWT.BORDER);
		textTotalSum.setEditable(false);
		GridData gd_textTotalSum = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_textTotalSum.widthHint = 140;
		textTotalSum.setLayoutData(gd_textTotalSum);
		
		Button button = new Button(composite_3, SWT.NONE);
		button.setText("\u041E\u043F\u043B\u0430\u0442\u0438\u0442\u044C \u0437\u0430\u043A\u0430\u0437");
		new Label(composite_3, SWT.NONE);
		
		final CTabItem tabItem4_Search = new CTabItem(tabFolder, SWT.NONE);
		tabItem4_Search.setText("\u041F\u043E\u0438\u0441\u043A");
		
		Composite composite_4 = new Composite(tabFolder, SWT.NONE);
		tabItem4_Search.setControl(composite_4);
		composite_4.setLayout(new GridLayout(2, false));
		
		searchText = new Text(composite_4, SWT.BORDER);
		searchText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button searchBtn = new Button(composite_4, SWT.NONE);
		searchBtn.setText("\u041F\u043E\u0438\u0441\u043A");
		
		table_2 = new Table(composite_4, SWT.BORDER | SWT.FULL_SELECTION);
		table_2.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		table_2.setHeaderVisible(true);
		table_2.setLinesVisible(true);
		
		TableColumn tableColumn_6 = new TableColumn(table_2, SWT.NONE);
		tableColumn_6.setWidth(150);
		tableColumn_6.setText("\u041D\u0430\u0437\u0432\u0430\u043D\u0438\u0435");
		
		TableColumn tableColumn_7 = new TableColumn(table_2, SWT.NONE);
		tableColumn_7.setWidth(90);
		tableColumn_7.setText("\u0420\u0430\u0437\u0440\u0430\u0431\u043E\u0442\u0447\u0438\u043A");
		
		TableColumn tableColumn_8 = new TableColumn(table_2, SWT.NONE);
		tableColumn_8.setWidth(80);
		tableColumn_8.setText("\u0426\u0435\u043D\u0430");
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		
		Label versionLabel = new Label(composite, SWT.NONE);
		versionLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 2, 1));
		versionLabel.setText("\u0432\u0435\u0440\u0441\u0438\u044F \u043F\u0440\u043E\u0433\u0440\u0430\u043C\u043C\u044B 0.1");
		
		//	Listeners
		tree.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				Point point = new Point(e.x, e.y);
				TreeItem item = tree.getItem (point);
				if (item != null) {
					//TODO: нормально переписать открытие нужной вкладки
					//tabFolder.getItems()
					//tabFolder.setSelection();
					String itemText = item.getText();
					System.out.println ("Tree item selected: " + itemText);
					if (itemText.equals("Главная")){
						tabFolder.setSelection(tabItem1_Home);
					}
					else if (itemText.equalsIgnoreCase("Корзина")){
						tabFolder.setSelection(tabItem3_Korzina);
					}
					else if (itemText.equalsIgnoreCase("Поиск")){
						tabFolder.setSelection(tabItem4_Search);
					}
					else if (itemText.equalsIgnoreCase("Все программы")){
						tabFolder.setSelection(tabItem2_SWList);
					}
				}
			}
		});
		//выбор столбца таблицы во вкладке "Список программ"
		table.addSelectionListener(new SelectionListener(){

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO доделать
				String itemText = ((TableItem)e.item).getText();
				System.out.println("Table item selected: " + itemText);
				//проверка, существует ли открытая вкладка с таким именем
				boolean createNewTab = true;
				for (int i = 0; i < selectedSW_tabItemList.size(); i++){
					if (itemText.equalsIgnoreCase(selectedSW_tabItemList.get(i).getText().trim())){
						createNewTab = false;
					}
				}
				if (createNewTab){
					CTabItem tabItemTemp = new CTabItem(tabFolder, SWT.CLOSE);
					selectedSW_tabItemList.add(tabItemTemp);
					tabItemTemp.setText(itemText.trim());
					
					//создание описания программы на создаваемой вкладке
					Composite compositeTemp = new Composite(tabFolder, SWT.NONE);
					tabItemTemp.setControl(compositeTemp);
					compositeTemp.setLayout(new GridLayout(1,false));
					Label name = new Label(compositeTemp,SWT.NONE);
					name.setText(itemText);
					Label developer = new Label(compositeTemp,SWT.NONE);
					developer.setText("Разработчик");
					Label description = new Label(compositeTemp,SWT.NONE);
					description.setText("Описание программы");
					Label price = new Label(compositeTemp,SWT.NONE);
					price.setText("Цена");
					Button buyBtn = new Button(compositeTemp, SWT.NONE);
					buyBtn.setText("Купить");
					//TODO: действия при покупке
					buyBtn.addSelectionListener(new SelectionListener(){

						@Override
						public void widgetSelected(SelectionEvent e) {
							tabFolder.setSelection(tabItem3_Korzina);
							// TODO Auto-generated method stub
							
						}

						@Override
						public void widgetDefaultSelected(SelectionEvent e) {
						}
					});
					
					//удаление вкладки из списка при ее коварном закрытии пользователем
					tabItemTemp.addDisposeListener(new DisposeListener() {
						@Override
						public void widgetDisposed(DisposeEvent e) {
							// TODO Auto-generated method stub
							selectedSW_tabItemList.remove(e.widget);
						}
					});
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
			
		});
	}
}
