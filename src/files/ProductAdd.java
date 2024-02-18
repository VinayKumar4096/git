package files;

public class ProductAdd {
	public static String addproduct()
	{
		String data = "productName:qwerty\r\n"
				+ "productAddedBy:{{userId}}\r\n"
				+ "productCategory:fashion\r\n"
				+ "productSubCategory:shirts\r\n"
				+ "productPrice:11500\r\n"
				+ "productDescription:Adidas Originals\r\n"
				+ "productFor:women";
		return data;
	}
	
}
