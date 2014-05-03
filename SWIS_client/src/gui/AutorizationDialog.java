package gui;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.browser.AuthenticationEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import gui.MainWindow;
import model.User;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.events.VerifyEvent;

public class AutorizationDialog{ //extends Dialog {

	protected User result;
	protected Shell shell;
	private Text textID;
	private Text textPassword;
	private Label labelMessages;
//	private MainWindow main;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
//	public AutorizationDialog(Shell parent, int style) {
//		super(parent, style);
//		setText("SWT Dialog");
//	}
	
	//TODO: запилить поиск
	
	public AutorizationDialog(){
	}

	/**
	 * Open the dialog.
	 * @return the result
	 * @wbp.parser.entryPoint
	 */
	public User open() {
		createContents();
		shell.open();
		shell.layout();
		Display display = Display.getDefault();//getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shell = new Shell();
		shell.setSize(350, 300);
		shell.setText("\u0418\u043D\u0442\u0435\u0440\u043D\u0435\u0442-\u043C\u0430\u0433\u0430\u0437\u0438\u043D \u043F\u0440\u043E\u0433\u0440\u0430\u043C\u043C\u043D\u043E\u0433\u043E \u043E\u0431\u0435\u0441\u043F\u0435\u0447\u0435\u043D\u0438\u044F.");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		
		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblNewLabel.setText("\u0417\u0434\u0440\u0430\u0432\u0441\u0442\u0432\u0443\u0439\u0442\u0435.\r\n\u0412\u044B \u0437\u0430\u043F\u0443\u0441\u0442\u0438\u043B\u0438 \u043A\u043B\u0438\u0435\u043D\u0442\u0441\u043A\u043E\u0435 \u043F\u0440\u0438\u043B\u043E\u0436\u0435\u043D\u0438\u0435 \r\n\u0438\u043D\u0442\u0435\u0440\u043D\u0435\u0442-\u043C\u0430\u0433\u0430\u0437\u0438\u043D\u0430 \u043F\u0440\u043E\u0433\u0440\u0430\u043C\u043C\u043D\u043E\u0433\u043E \u043E\u0431\u0435\u0441\u043F\u0435\u0447\u0435\u043D\u0438\u044F.\r\n\u0415\u0441\u043B\u0438 \u0432\u044B \u0443\u0436\u0435 \u0437\u0430\u0440\u0435\u0433\u0438\u0441\u0442\u0440\u0438\u0440\u043E\u0432\u0430\u043D\u044B, \u0432\u0432\u0435\u0434\u0438\u0442\u0435 \u0441\u0432\u043E\u0439 \r\n\u0438\u0434\u0435\u043D\u0442\u0438\u0444\u0438\u043A\u0430\u0446\u0438\u043E\u043D\u043D\u044B\u0439 \u043D\u043E\u043C\u0435\u0440 \u0438 \u043F\u0430\u0440\u043E\u043B\u044C \u0438 \u043D\u0430\u0436\u043C\u0438\u0442\u0435 \"\u0412\u0445\u043E\u0434\".\r\n\u0415\u0441\u043B\u0438 \u043D\u0435\u0442 - \u043D\u0430\u0436\u043C\u0438\u0442\u0435 \"\u0420\u0435\u0433\u0438\u0441\u0442\u0440\u0430\u0446\u0438\u044F\".");
		
		Label label = new Label(composite, SWT.NONE);
		label.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		label.setText("Идентификационный номер (только цифры)");
		
		textID = new Text(composite, SWT.BORDER);
		textID.addVerifyListener(new VerifyListener() {
			public void verifyText(VerifyEvent e) {
				// если хотя бы один из новых вводимых
				// символов не цифра или точка -
				// ввод отменяется, они не заносятся в поле.
				for (int i = 0; i < e.text.length(); i++) {
					if ("0123456789".indexOf(e.text.charAt(i)) == -1) {
						e.doit = false;
						break;
					}
				}
			}
		});
		GridData gd_textID = new GridData(SWT.LEFT, SWT.CENTER, true, false, 2, 1);
		gd_textID.widthHint = 200;
		textID.setLayoutData(gd_textID);
		
		Label label_1 = new Label(composite, SWT.NONE);
		label_1.setText("\u041F\u0430\u0440\u043E\u043B\u044C");
		new Label(composite, SWT.NONE);
		
		textPassword = new Text(composite, SWT.BORDER | SWT.PASSWORD);
		GridData gd_textPassword = new GridData(SWT.LEFT, SWT.CENTER, true, false, 2, 1);
		gd_textPassword.widthHint = 200;
		textPassword.setLayoutData(gd_textPassword);
		
		Button buttonLogin = new Button(composite, SWT.NONE);
		buttonLogin.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String ID, password;
				ID = textID.getText();
				password = textPassword.getText();
				if (ID == ""){
					//TODO: если это действиетльно ИД, проверка на только цифры
					labelMessages.setText("Введите идентификационный номер!");
					return;
				}
				if (password == ""){
					labelMessages.setText("Введите пароль!");
					return;
				}
				User user = MainWindow.auth(ID, password);
				if (user == null){
					labelMessages.setText("Неверный идентификационный номер или пароль!");
				}
				else{
					result = user;
					shell.dispose();
				}
			}
		});
		GridData gd_buttonLogin = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_buttonLogin.widthHint = 80;
		buttonLogin.setLayoutData(gd_buttonLogin);
		buttonLogin.setText("\u0412\u0445\u043E\u0434");
		
		Button buttonRegister = new Button(composite, SWT.NONE);
		buttonRegister.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		buttonRegister.setText("\u0420\u0435\u0433\u0438\u0441\u0442\u0440\u0430\u0446\u0438\u044F");
		buttonRegister.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				labelMessages.setText("Еще не запилено");
			}
		});

		labelMessages = new Label(composite, SWT.NONE);
		labelMessages.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));
		labelMessages.setText("test");
	}
}
