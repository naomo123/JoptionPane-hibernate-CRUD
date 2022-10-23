package app;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import javax.swing.JOptionPane;

import models.Producto;

public class MainApp {

	private static final int INPUT_VALUE_PROPERTY = 0;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int opcion = 9;

		Producto producto;

		EntityManager entity = JPAUtil.getEntityManagerFactory().createEntityManager();

		while (opcion != 5) {

			String[] botones = { "1. Crear Producto", "2. Buscar Producto", "3. Actualizar Producto",
					"4. Eliminar Producto", "6. Ver lista de productos", "5. Salir" };
			String[] salidaE = { "Si" };
			opcion = JOptionPane.showOptionDialog(null, "Elija una opción:", "Inicio", JOptionPane.DEFAULT_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, botones, null);

			switch (opcion) {

			case 0:

				producto = new Producto();

				String inputValueName = JOptionPane.showInputDialog("Digite el nombre del producto");

				while (inputValueName.isEmpty()) {
					inputValueName = JOptionPane.showInputDialog("Tiene que digitar el nombre del producto");
				}
				String inputValueMarca = JOptionPane.showInputDialog("Digite la marca del producto:");
				while (inputValueMarca.isEmpty()) {
					inputValueMarca = JOptionPane.showInputDialog("Tiene que digitar la marca del producto:");
				}
				producto.setNombre(inputValueName);
				String inputValueDescrip = JOptionPane.showInputDialog("Digite la descripcion del producto:");
				while (inputValueDescrip.isEmpty()) {
					inputValueDescrip = JOptionPane.showInputDialog("Tiene que digitar la descripcion del producto:");
				}
				producto.setMarca(inputValueMarca);
				String inputValueStock = JOptionPane.showInputDialog("Digite las existencias del producto:");
				while (inputValueStock.isEmpty()) {
					inputValueStock = JOptionPane.showInputDialog("Tiene que digitar las existencias del producto:");
					producto.setStock(Integer.parseInt(inputValueStock));
				}
				producto.setDescripcion(inputValueDescrip);
				String inputValuePrecio = JOptionPane.showInputDialog("Digite el precio del producto:");
				while (inputValuePrecio.isEmpty()) {
					inputValuePrecio = JOptionPane.showInputDialog("Tiene que digitar el precio valido del producto:");
				}
				producto.setPrecio(Double.parseDouble(inputValuePrecio));

				entity.getTransaction().begin();
				entity.persist(producto);
				entity.getTransaction().commit();
				entity.refresh(producto);

				JOptionPane.showMessageDialog(null, "El producto es " + producto);
				JOptionPane.showMessageDialog(null, "Producto registrado..");

				break;

			case 1:
				List<Producto> listaProductos = new ArrayList<>();
				Query query = entity.createQuery("SELECT p FROM Producto p");
				listaProductos = query.getResultList();

				String inputIdb = JOptionPane.showInputDialog("Digita el id del producto");

				producto = new Producto();
				producto = entity.find(Producto.class, Integer.parseInt(inputIdb));

				if (producto != null) {
					JOptionPane.showMessageDialog(null, producto);

				} else {

					JOptionPane.showMessageDialog(null, "Producto no encontrado... " + listaProductos);

				}

				break;
			case 2:
				String inputIda = JOptionPane.showInputDialog("Digita el id del producto que deseas actualizar");
				while (inputIda.isEmpty()) {
					inputIda = JOptionPane
							.showInputDialog("Tiene que digitar el id del producto que deseas actualizar");
				}
				producto = new Producto();
				producto = entity.find(Producto.class, Integer.parseInt(inputIda));

				if (producto != null) {

					String inputEditName = JOptionPane.showInputDialog(null,
							producto + "\n Si desea editar, Digite el nombre del producto:", producto.getNombre());
					while (inputEditName.isEmpty()) {
						inputEditName = JOptionPane.showInputDialog("Tiene que digitar el nombre del producto:");
					}
					producto.setNombre(inputEditName);

					String inputEditMarca = JOptionPane.showInputDialog(null,
							producto + "\n Si desea editar, Digite la marca del producto:",producto.getMarca());
					while (inputEditMarca.isEmpty()) {
						inputEditMarca = JOptionPane.showInputDialog("Tiene que digitar la marca del producto:");
					}
					producto.setMarca(inputEditMarca);

					String inputEditDescrip = JOptionPane.showInputDialog(null,
							producto + "\n Si desea editar, Digite la descripcion del producto:", producto.getDescripcion());
					while (inputEditDescrip.isEmpty()) {
						inputEditDescrip = JOptionPane
								.showInputDialog("Tiene que digitar la descripcion del producto:");
					}
					producto.setDescripcion(inputEditDescrip);

					String inputEditStock = JOptionPane.showInputDialog(null,
							producto + "\n Si desea editar, Digite las existencias del producto:", producto.getStock());
					while (inputEditStock.isEmpty()) {
						inputEditStock = JOptionPane.showInputDialog("Tiene que digitar las existencias del producto:");
					}
					producto.setStock(Integer.parseInt(inputEditStock));

					String inputEditPrecio = JOptionPane.showInputDialog(null,
							producto + "\n Si desea editar, Digite el precio del producto:", producto.getPrecio());
					while (inputEditPrecio.isEmpty()) {
						inputEditPrecio = JOptionPane.showInputDialog("Tiene que digitar el precio del producto:");
					}
					producto.setPrecio(Double.parseDouble(inputEditPrecio));

					entity.getTransaction().begin();
					entity.merge(producto);
					entity.getTransaction().commit();
					JOptionPane.showMessageDialog(null, producto+"\n Producto actualizado.. ");

				} else {
					JOptionPane.showMessageDialog(null, "Producto no encontrado.... ");

				}
				break;
			case 3:
				String inputIde = JOptionPane.showInputDialog("Digita el id del producto que deseas actualizar");

				producto = new Producto();
				producto = entity.find(Producto.class, Integer.parseInt(inputIde));

				if (producto != null) {

					JOptionPane.showMessageDialog(null, producto);
					entity.getTransaction().begin();
					entity.remove(producto);
					entity.getTransaction().commit();

					JOptionPane.showMessageDialog(null, "Producto eliminado...\n" + producto);

				} else {
					JOptionPane.showMessageDialog(null, "Producto no encontrado..");
				}
				break;
			case 4:

				List<Producto> listaProductosv = new ArrayList<>();
				Query query2 = entity.createQuery("SELECT p FROM Producto p");
				listaProductosv = query2.getResultList();

				JOptionPane.showMessageDialog(null, "\n" + listaProductosv);
				break;

			case 5:
				opcion = 5;
				entity.close();
				JPAUtil.shutdown();
				break;

			default:
				System.out.println("Opción no válida\n");
				break;
			}
		}
	}

}
