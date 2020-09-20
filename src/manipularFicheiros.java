import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

public class manipularFicheiros {
	
	File f;
	OutputStream os;
	InputStream is;
	boolean ficheiroValido;

	public manipularFicheiros(String nomeAbsoluto) {
		f = new File(nomeAbsoluto);
		try {
			f.createNewFile();
			ficheiroValido = true;
		} catch (IOException e) {
			ficheiroValido = false;
		}

	}

	public void EscreverString(String s) {
		try {
			String c = new String(s);
			c += "=";
			System.out.println(Arrays.toString(c.getBytes()));
			os.write(c.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void EscreverBoolean(boolean b) {
		try {
			String mark = "";
			int value = (byte) (b == true ? 1 : 0);
			mark += String.valueOf(value) + "=";
			os.write(mark.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void EscreverInt(int i) {
		String integer = String.valueOf(i);
		integer += "=";
		try {
			os.write(integer.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int CarregarInt() {
		String number = "";
		try {
			while (is.available() > 0) {
				int numericValue = Character.getNumericValue(is.read());
				System.out.println("CarregarInt " + numericValue);
				if (numericValue != -1) {
					number += numericValue;
				} else {
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return Integer.parseInt(number);
	}

	public String CarregarString() {
		String number = "";
		try {
			while (is.available() > 0) {
				char numericValue = (char) is.read();

				System.out.println("CarregarString " + numericValue);
				if (numericValue != 42) {
					number += numericValue;
				} else {
					break;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return number;
	}

	public boolean CarregarBoolean() {
		boolean b = false;
		try {
			while (is.available() > 0) {
				int numericValue = Character.getNumericValue(is.read());
				System.out.println("CarregarBool " + numericValue);
				if (numericValue != -1) {
					b = (numericValue == 1) ? true : false;
				} else {
					break;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
	}

	public void VoltarInicio() {

	}


	public int[] CarregarComandos() {
		String val = "";
		try {
			while (is.available() > 0) {
				int numericValue = Character.getNumericValue(is.read());
				val+=numericValue;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		String[] value = val.split("=");
		
		int[] intvalue = new int[value.length];
		for(int i = 0 ; i < intvalue.length ; i++) {
			intvalue[i] = Integer.parseInt(value[i]);
		}
		return intvalue;
	}

	public boolean isFicheiroValido() {
		return ficheiroValido;
	}

	public boolean osOpen() {
		try {
			os = new FileOutputStream(f);
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void osClose() {
		try {
			os.flush();
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public boolean isOpen() {
		try {
			is = new FileInputStream(f);
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void isClose() {
		try {
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
