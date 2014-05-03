package gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.*;

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
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.events.SelectionAdapter;

public class MainWindow {

//	private static final String HOME_GREETINGS = "Здравствуйте, ";
	private static final String WINDOW_TITLE = "Клиентская программа интернет-магазина программного обеспечения";
	protected Shell shell;
	private Table table;
	private Table table_1;
	private Text searchText;
	private Table table_2;
	private Text quickSearchText;
	private Text textTotalSum;
	TreeItem treeItem_Home;
	TreeItem treeItem_Korzina;
	TreeItem treeItem_Search;
	TreeItem treeItem_All_SW;
	TreeItem treeItem_categories;
	/**
	 * Вызвать окно авторизации при закрытии (нажата кнопка "Выйти")
	 */
	boolean repeatAuthorization = false;
	
	
	/**
	 * Список продуктов, отображающийся в таблице
	 */
	private List<Product> products;
	/**
	 * Хэш-таблица открытых вкладок с описаниями продуктов:<br>
	 * Объект вкладки -> Объект продукта
	 */
	private HashMap <CTabItem, Product > selectedSW_TabItemIDmap;
	//private List <CTabItem> selectedSW_tabItemList;
	
	/**
	 * текущий пользователь
	 */
	private User currentUser;
	private String[] categories;
	/**
	 * корзина
	 */
	private Cart korzina;
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		boolean repeatAuth;
		try {
			do{
				//авторизация
			AutorizationDialog ad = new AutorizationDialog();
			User user = ad.open();
			if (user == null){
				return;
			}
			MainWindow window = new MainWindow(user);
			repeatAuth = window.open();
			} while (repeatAuth);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public MainWindow(User user){
		currentUser = user;
	}

	/**
	 * Open the window.
	 * @wbp.parser.entryPoint
	 */
	public boolean open() {
		repeatAuthorization = false;
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return repeatAuthorization;
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		//final List<CTabItem> selectedSW_tabItemList = new ArrayList<CTabItem>();
		selectedSW_TabItemIDmap = new HashMap <CTabItem, Product> ();
		shell = new Shell();
		shell.setSize(700, 550);
		shell.setText(WINDOW_TITLE);
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		dataInit();
		
		Composite composite = new Composite(shell, SWT.NONE);
		GridLayout gl_composite = new GridLayout(7, false);
		composite.setLayout(gl_composite);
		
		final Tree tree = new Tree(composite, SWT.BORDER);

		GridData gd_tree = new GridData(SWT.FILL, SWT.FILL, false, true, 1, 2);
		gd_tree.widthHint = 120;
		tree.setLayoutData(gd_tree);
		
		TreeColumn treeColumn = new TreeColumn(tree, SWT.LEFT);
		treeColumn.setWidth(200);
		treeColumn.setText("\u0413\u043B\u0430\u0432\u043D\u0430\u044F");
		
		treeItem_Home = new TreeItem(tree, SWT.NONE);
		treeItem_Home.setText(new String[] {});
		treeItem_Home.setText("\u0413\u043B\u0430\u0432\u043D\u0430\u044F");
		treeItem_Home.setExpanded(true);
		
		treeItem_Korzina = new TreeItem(tree, SWT.NONE);
		treeItem_Korzina.setText("\u041A\u043E\u0440\u0437\u0438\u043D\u0430");
		
		treeItem_Search = new TreeItem(tree, SWT.NONE);
		treeItem_Search.setText("\u041F\u043E\u0438\u0441\u043A");
		
		treeItem_All_SW = new TreeItem(tree, SWT.NONE);
		treeItem_All_SW.setText("\u0412\u0441\u0435 \u043F\u0440\u043E\u0433\u0440\u0430\u043C\u043C\u044B");
		
		treeItem_categories = new TreeItem(tree, SWT.NONE);
		treeItem_categories.setText("\u041F\u043E \u043A\u0430\u0442\u0435\u0433\u043E\u0440\u0438\u044F\u043C");
		
		Label labelUserID = new Label(composite, SWT.BORDER);
		GridData gd_labelUserID = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_labelUserID.widthHint = 60;
		labelUserID.setLayoutData(gd_labelUserID);
		
		Label labelUserFName = new Label(composite, SWT.NONE);
		GridData gd_labelUserFName = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_labelUserFName.widthHint = 100;
		labelUserFName.setLayoutData(gd_labelUserFName);
		
		Label labelUserSName = new Label(composite, SWT.NONE);
		GridData gd_labelUserSName = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_labelUserSName.widthHint = 120;
		labelUserSName.setLayoutData(gd_labelUserSName);
		
		if (currentUser != null){
			labelUserID.setText(String.valueOf(currentUser.getId()));
			labelUserFName.setText(currentUser.getFname());
			labelUserSName.setText(currentUser.getSname());
		}
		else{
			labelUserID.setText("");
			labelUserFName.setText("Нет данных");
			labelUserSName.setText("о пользователе");
		}
		
		Label label_11 = new Label(composite, SWT.BORDER);
		label_11.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		
		//new Label(composite, SWT.NONE);//TODO: WTF?
		
		quickSearchText = new Text(composite, SWT.BORDER);
		quickSearchText.setToolTipText("\u0411\u044B\u0441\u0442\u0440\u044B\u0439 \u043F\u043E\u0438\u0441\u043A");
		GridData gd_quickSearchText = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
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
		GridData gd_tabFolder = new GridData(SWT.FILL, SWT.FILL, true, true, 6, 1);
		gd_tabFolder.widthHint = 469;
		gd_tabFolder.heightHint = 408;
		tabFolder.setLayoutData(gd_tabFolder);
		tabFolder.setSelectionBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		
		final CTabItem tabItem1_Home = new CTabItem(tabFolder, SWT.NONE);
		tabItem1_Home.setText("\u0413\u043B\u0430\u0432\u043D\u0430\u044F");
		
		Composite composite_1 = new Composite(tabFolder, SWT.BORDER);
		tabItem1_Home.setControl(composite_1);
		composite_1.setLayout(new GridLayout(1, false));
		
		String str;
		str = (currentUser == null) ? "Ошибка - не найдены данные пользователя. Выйдите и войдите еще раз." : 
			"Здравствуйте, " + currentUser.getFname() + "!";
		Label labelHome = new Label(composite_1, SWT.NONE);
		labelHome.setText(str);
		
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
		//Таблица - выбор столбца таблицы во вкладке "Список программ" и открытие описания программы на новой вкладке
		table.addSelectionListener(new SelectionListener(){

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO доделать
				String itemText = ((TableItem)e.item).getText();
				System.out.println("Table item selected: " + itemText);
				int selectionIndex = table.getSelectionIndex();
				if (selectionIndex >= 0){
					Product selectedProduct = products.get(selectionIndex);
					//проверка, существует ли открытая вкладка с таким именем
					boolean createNewTab = ! selectedSW_TabItemIDmap.containsValue(selectedProduct);
//					boolean createNewTab = true;
//					for (int i = 0; i < selectedSW_tabItemList.size(); i++){
//						if (itemText.equalsIgnoreCase(selectedSW_tabItemList.get(i).getText().trim())){
//							createNewTab = false;
//						}
//					}
					
					if (createNewTab && (selectedProduct != null)){
						CTabItem tabItemTemp = new CTabItem(tabFolder, SWT.CLOSE);
						//selectedSW_tabItemList.add(tabItemTemp);
						selectedSW_TabItemIDmap.put(tabItemTemp, selectedProduct);
						tabItemTemp.setText(itemText.trim());
						
						//TODO: создание описания программы на создаваемой вкладке
						Composite compositeTemp = new Composite(tabFolder, SWT.NONE);
						tabItemTemp.setControl(compositeTemp);
						compositeTemp.setLayout(new GridLayout(1,false));
						Label name = new Label(compositeTemp,SWT.NONE);
						name.setText(selectedProduct.getName());
						Label developer = new Label(compositeTemp,SWT.NONE);
						developer.setText(selectedProduct.getDeveloper());
						//запрос описания продукта
						String desc = selectedProduct.getDecsription();
						if ((desc == null) || desc == ""){
							//TODO: подключение отсутствует
							selectedProduct.setDescription(getProductDescription(selectedProduct.getId()));
						}
						
//						Label description = new Label(compositeTemp,SWT.NONE);
//						description.setText(selectedProduct.getDecsription());
						
						Text description = new Text(compositeTemp,SWT.READ_ONLY | SWT.V_SCROLL | SWT.H_SCROLL);
						description.setText(selectedProduct.getDecsription());
						GridData gd = new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1);
//						gd_browser.heightHint = 150;
//						gd_browser.widthHint = 300;
						description.setLayoutData(gd);
						
						
						Label price = new Label(compositeTemp,SWT.NONE);
						price.setText(String.valueOf(selectedProduct.getPrice()));
						
						// ! браузер для отображения описания содержащего HTML-теги.
//						Browser browser = new Browser(compositeTemp, SWT.NONE);
//						GridData gd_browser = new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1);
//						gd_browser.heightHint = 150;
//						gd_browser.widthHint = 300;
//						browser.setLayoutData(gd_browser);
//						browser.setText("text<br><b>fat text</b><br>");
						
						Button buyBtn = new Button(compositeTemp, SWT.NONE);
						buyBtn.setText("Купить");
						//TODO: действия при покупке
						buyBtn.addSelectionListener(new SelectionListener(){

							@Override
							public void widgetSelected(SelectionEvent e) {
								// TODO покупка товара
								//buyProduct();
								CTabFolder tf = (CTabFolder)( ((Button)e.widget).getParent().getParent());
								int selectedTabItem = tf.getSelectionIndex();
								if (selectedTabItem < 0) return;
								Product product = selectedSW_TabItemIDmap.get(tf.getItem(selectedTabItem));
								if (product != null){
									buyProduct(product);
									korzina = getCart(currentUser);
									tabFolder.setSelection(tabItem3_Korzina);
								}
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
								//selectedSW_tabItemList.remove(e.widget);
								selectedSW_TabItemIDmap.remove(e.widget);
							}
						});
					}
				}
				
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
			
		});
		
		Button buttonLogout = new Button(composite, SWT.NONE);
		buttonLogout.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				repeatAuthorization = true;
				shell.close();
			}
		});
		buttonLogout.setText("\u0412\u044B\u0445\u043E\u0434");
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		
		Label versionLabel = new Label(composite, SWT.NONE);
		versionLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 2, 1));
		versionLabel.setText("\u0432\u0435\u0440\u0441\u0438\u044F \u043F\u0440\u043E\u0433\u0440\u0430\u043C\u043C\u044B 0.1");
		
		//categories list first init
		updateCategoriesTree();
		
		//product list first initialization
		updateProductsTable(false);
		
		//------------------------------------------------
		//	Listeners
		//Дерево слева - при нажатии на элемент дерева слева выбор соответствующей ему вкладки в главном окне
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
					else {
						if (itemText.equalsIgnoreCase("Все программы")){
							//открытие списка всех программ
							updateProductsTable(true);
						}
						else{
							//открытие списка программ нужной категории
							//если понадобится, сделать проверку в списке категорий
							updateProductsTableByCategory(itemText);
						}
						//updateProductsTable(true);
						tabFolder.setSelection(tabItem2_SWList);
					}
				}
			}
		});
//		tabFolder.addSelectionListener(new SelectionListener() {
//			
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//				// TODO проверить обновление корзины
//				if (((CTabItem)e.item) == tabItem3_Korzina){
//					korzina = getCart(currentUser);
//				}
//			}
//			
//			@Override
//			public void widgetDefaultSelected(SelectionEvent e) {
//			}
//		});
	}

	private void updateCategoriesTree() {
		TreeItem treeItem;
		if (categories != null){
			treeItem_categories.removeAll();
			for (int i = 0; i < categories.length; i++){
				treeItem = new TreeItem(treeItem_categories, SWT.NONE);
				treeItem.setText(categories[i]);
			}			
		}
		treeItem_categories.setExpanded(true);
	}
	/**
	 * Запрос списка продуктов у сервера, и заполнение им таблицы.
	 * @param updateProductList true - запрашивать список продуктов у сервера, false - использовать старый список.
	 */
	private void updateProductsTable(boolean updateProductList) {
		TableItem tableItem;
		if (updateProductList){
			products = getProducts();
		}
		if ((products != null) && (!products.isEmpty())) {
			table.removeAll();
			for (int i = 0; i < products.size(); i++) {
				tableItem = new TableItem(table, SWT.NONE);
				tableItem.setText(new String[] { products.get(i).getName(),
						products.get(i).getDeveloper(),
						String.valueOf(products.get(i).getPrice()) });
			}
		}
	}
	
	/**
	 * Запрос списка продуктов определенной категории у сервера, и заполнение им таблицы.
	 * @param category - название категории, если это не категория - будет возвращен пустой список
	 */
	private void updateProductsTableByCategory(String category) {
		TableItem tableItem;
		products = getProductsByCategory(category);
		if ((products != null) && (!products.isEmpty())) {
			table.removeAll();
			for (int i = 0; i < products.size(); i++) {
				tableItem = new TableItem(table, SWT.NONE);
				tableItem.setText(new String[] { products.get(i).getName(),
						products.get(i).getDeveloper(),
						String.valueOf(products.get(i).getPrice()) });
			}
		}
	}
	//TODO: отображение корзины
	//---------------------------------------------------------
	/**
	 * инициализация данных - отправка запросов при первом запуске
	 */
	void dataInit(){
		products = getProducts();
		categories = getCategories();
		//TODO: инициализация данных - отправка запросов при первом запуске
	}
	
	
	
	
	
	//----------------------------------------------------------------------
	//	запросы к серверу
	//----------------------------------------------------------------------
	/**
	 * запрос авторизации пользователя
	 * @param login
	 * @param password
	 * @return данные о пользователе, при неверной паре логин-пароль возвращает null
	 */
	public static User auth(String login, String password){
		//TODO: запрос авторизации, нужно получить от сервера подтверждение/отклонение входа, имя/фамилию и номер карты пользователя
//		----------------
//		Передача: String login, String password
//		Прием: boolean authSuccess // успех/неудача авторизации
//		int id;	//ИД пользователя
//		long cardNumber; //номер карты
//		String fname, sname; //имя фамилия
//		----------------		
		User user;
		int id;
		long cardNumber;
		String fname, sname;
		boolean authSuccess = false;
		//здесь будет запрос
		authSuccess = true;
		if (authSuccess){
//			user = new User(id, fname, sname, cardNumber);
			user = new User(Integer.valueOf(login), "ivan", "testov", 10);
			return user;
		}
		return null;
	}
	/**
	 * запрос регистрации пользователя
	 * @param user инфа введенная пользователем
	 * @return true - зареган, false - нет.
	 */
	public static boolean register(User user){
		boolean registerSuccess = false;
		//TODO: запрос регистрации пользователя, отправляются значения всех полей введенные пользователем,
		// принимается подтверждение или отклонение регистрации.
//		----------------
//		Передача:
//		int id;	//ИД пользователя
//		private String fname;
//		private String sname;
//		long cardNumber; //номер карты
//		private String passwordHash; //хэш пароля
//		Прием: boolean registerSuccess // успех/неуспех авторизации

//		----------------	
		return registerSuccess;
	}
	/**
	 * запрос списка программных продуктов
	 * @return
	 */
	List<Product> getProducts(){
		List <Product> newProducts;
		//TODO: запрос списка продуктов у сервера, нужно получить массив или список (java.util.List) объектов Product, или значения полей этих объектов.
//		----------------
//		Передача: -
//		Прием: List<Product> или Product[]
//		model.Product поля классса:
//		private int id;	//идентификатор - первичный ключ БД
//		private String name;
//		private String decsription;
//		private String category;
//		private double price;
//		private String developer;
//		----------------	
		newProducts = new ArrayList<Product>();
		//заглушка
		newProducts.add(new Product(0,"Microsoft Office 2010","","Офис", 3000, "Microsoft"));
		newProducts.add(new Product(1,"Windows 7 x64",null,"Операционные системы", 2000, "Microsoft"));
		newProducts.add(new Product(2,"ESET AV 6","ESET большое длинное описание аааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааа\nаааааааааааааааааааааааааааааааааааааааааааа1111!","Антивирусы", 500, "ESET"));
		return newProducts;
	}
	
	/**
	 * запрос списка программных продуктов определенной категории - 
	 * сейчас сделано как запрос списка всех продуктов (выше) и фильтрация по категории
	 * @return
	 */
	List<Product> getProductsByCategory(String category){
		List<Product> newProducts, newProductsofCategory;
		//TODO: если понадобится, запрос списка продуктов определенной категории у сервера.
		newProducts = getProducts();
		newProductsofCategory = new ArrayList<Product>();
		//добавление в результат только продуктов с нужной категорией
		for (int i = 0; i < newProducts.size(); i++){
			if (newProducts.get(i).getCategory().equalsIgnoreCase(category)){
				newProductsofCategory.add(newProducts.get(i));
			}
		}
		return newProductsofCategory;
	}
	/**
	 * запрос списка категорий ПО
	 */
	String[] getCategories(){
		//TODO: запрос списка категорий ПО у сервера
//		----------------
//		Передача: -
//		Прием: String[] categories;
//		----------------	
		return new String[] {"Антивирусы", "Офис", "Операционные системы"};
	}
	/**
	 * запрос описания продукта у сервера - при вызове окна описания продукта 
	 * остальные данные уже будут загружены раньше для отображения в таблице всех продуктов.
	 * @param productID
	 * @return product description
	 */
	//TODO: запрос описания продукта у сервера
	String getProductDescription(int productID){
//		----------------
//		Передача: int productID
//		Прием: String description // описание продукта
//		----------------	
		return "product description";
	}
	/**
	 * запрос добавления продукта в корзину
	 * @param product
	 */
	void buyProduct(Product product){
		//product.getId();
		//currentUser.getId();
		//TODO:запрос добавления продукта в корзину
		//отправляется ID продукта, ID пользователя (если нужно, добавить количество).
//		----------------
//		Передача: int productID, int UserID
//		Прием: ничего или boolean buySuccess успех/неуспех покупки
//		----------------	
	}
	/**
	 * запрос содержимого корзины опеределенного пользователя
	 * @param user пользователь
	 * @return
	 */
	Cart getCart(User user){
		HashMap <Product, Integer > mapProductQuantity = new HashMap<Product, Integer>();
		//TODO: запрос
		//отправление сведений о пользователе (ID), получение списка покупаемых продуктов и количество каждого продукта.
//		----------------
//		Передача: String login, String password
//		Прием: HashMap <Product, Integer > 
//		или Product[] products список продуктов 
//		int[] quantities список количеств продуктов
//		----------------	
		
//		for (int i = 0; i < productCount; i++){
//			mapProductQuantity.put(products[i], quantity[i]);
//		}
		return new Cart(user,mapProductQuantity);
	}
}
