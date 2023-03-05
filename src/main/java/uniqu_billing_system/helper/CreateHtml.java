package uniqu_billing_system.helper;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import uniqu_billing_system.model.DescriptioOfGoods;
import uniqu_billing_system.model.Extra;
import uniqu_billing_system.model.Order;

public class CreateHtml {
	public boolean createHtml(Order addeddOrder, List<DescriptioOfGoods> allDogs, List<Extra> allExtra) {
		String o1="Y:\\htmlbillls\\"+ addeddOrder.getInvoice_number() + ".html";
		String o1Sohail="F:\\Sampla\\html\\"+ addeddOrder.getInvoice_number() + ".html";
		String bill_html="C:\\Users\\nazim\\Downloads\\BillingSystem\\src\\main\\webapp\\bill.html";
		String bill_html_Sohail="F:\\Sampla\\bill.html";
		boolean result = false;

		List<String> lines = new ArrayList<String>();
		try {
			File obj1 = new File(o1Sohail);
			File obj2 = new File(bill_html_Sohail);

			if (!(obj1.exists())) {
				obj1.createNewFile();
			}
			Scanner reader = new Scanner(obj2);
			FileWriter writer = new FileWriter(
					"Y:\\htmlbillls\\"
							+ addeddOrder.getInvoice_number() + ".html");
			while (reader.hasNextLine()) {
				String data = reader.nextLine();
				String line = "";
				if (data.contains("client_name")) {
					line = data.replaceFirst(">", ">" + addeddOrder.getClient_name());
				} else if (data.contains("order_date")) {
					line = data.replaceFirst(">", ">Date: " + addeddOrder.getOrder_date().toString().substring(0,11));
				} else if (data.contains("invoice_number")) {
					line = data.replaceFirst(">", ">Invoice Number:<b>" + addeddOrder.getInvoice_number()+"</b>");
				} else if (data.contains("inWords")) {
					long inv = addeddOrder.getGrand_total().longValue();
					line = data.replaceFirst(">", ">" + Number2Word.convertNumberToWord(inv));
				} else if (data.contains("total_amount")) {
					line = data.replaceFirst(">", ">" + addeddOrder.getTotal().longValue());
				} else if (data.contains("grand_total")) {
					line = data.replaceFirst(">", ">" + addeddOrder.getGrand_total().longValue());
				} else if (data.contains("<begin>")) {
					String row_html="C:\\Users\\nazim\\Downloads\\BillingSystem\\src\\main\\webapp\\row.html";
					String row_html_sohail="F:\\Sampla\\row.html";
					for (DescriptioOfGoods s : allDogs) {
						
						File obj3 = new File(
								row_html_sohail);
						Scanner rows = new Scanner(obj3);
						lines.add("<tr>");
						while (rows.hasNextLine()) {
							String datad = rows.nextLine();

							if (datad.contains("srno")) {
								lines.add(datad.replaceFirst(">", ">" + s.getId()) + "\n");
							} else if (datad.contains("description")) {
								lines.add(datad.replaceFirst(">", ">" + s.getDescription()) + "\n");
							} else if (datad.contains("dimension-h")) {
								lines.add(datad.replaceFirst(">", ">" + s.getHeight().longValue()) + "\n");
							} else if (datad.contains("dimension-w")) {
								lines.add(datad.replaceFirst(">", ">" + s.getWidth().longValue()) + "\n");
							} else if (datad.contains("quantity")) {
								lines.add(datad.replaceFirst(">", ">" + s.getQuantity()) + "\n");
							} else if (datad.contains("sqrfi")) {
								lines.add(datad.replaceFirst(">", ">" + s.getAmount_sqr_f_i().longValue()) + "\n");
							} else if (datad.contains("rate")) {
								lines.add(datad.replaceFirst(">", ">" + s.getRate().longValue()) + "\n");
							} else if (datad.contains("amount")) {
								lines.add(datad.replaceFirst(">", ">" + s.getAmount().longValue()) + "\n");
							}else {
								lines.add(datad);
							}
						}
						lines.add("</tr>");
						rows.close();
						
					}
					for(int i=allDogs.size()+1;i<13;i++) {
						File obj3 = new File(row_html_sohail);
						Scanner rows = new Scanner(obj3);
						
						while (rows.hasNextLine()) {
							String datad = rows.nextLine();
							lines.add(datad.replaceFirst(">x", ">"));
						}
						
						rows.close();
					}

				} else if (data.contains("<beginExtra>")) {
					String extra_html="C:\\Users\\nazim\\Downloads\\BillingSystem\\src\\main\\webapp\\extra.html";
					String extra_html_sohail="F:\\Sampla\\extra.html";
					for (Extra s : allExtra) {
						
						File obj4 = new File(
								extra_html_sohail);
						Scanner rows = new Scanner(obj4);
						lines.add("<tr>");
						while (rows.hasNextLine()) {
							String datad = rows.nextLine();

							if (datad.contains("extraDescription")) {
								lines.add(datad.replaceFirst(">", ">" + s.getDescription()) + "\n");
							} else if (datad.contains("extraAmount")) {
								lines.add(datad.replaceFirst(">", ">" + s.getAmount().longValue()) + "\n");
							}
						}
						lines.add("</tr>");
						rows.close();
					}
				} else {
					line = data;
				}
				lines.add(line + "\n");
			}
			for (String lin : lines) {
				writer.write(lin);
			}

			reader.close();
			writer.close();
			result = true;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}
}
