/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

/**
 *
 * @author Administrator
 */
import java.awt.Component;
import java.awt.Container;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class ClearForm {
	public static void clearFields(Container container) {
		Component[] components = container.getComponents();
		for (Component component : components) {
			if (component instanceof JTextField) {
				JTextField textField = (JTextField) component;
				textField.setText("");
			} else if (component instanceof JPasswordField) {
				JPasswordField passwordField = (JPasswordField) component;
				passwordField.setText("");
			}
		}
	}
}
