import java.util.Arrays;

public class testes {
	
	//se for estes IDs ele tem de pegar os dois a seguir
	private static final int RetaID = 0;
	private static final int RetaguardaID = 1;
	
	//ser for estes IDs ele tem de pegar os 3 a seguir
	private static final int CurvarEsqID = 2;
	private static final int CurvarDirID = 3;
	
	//se for este IDs ele tem de pegar os dois a seguir
	private static final int PararID = 6;
	
	public static void main (String[] args) {
		//String brunogordo[] = new  [10];
		//System.out.println(Arrays.toString(brunogordo));
		String a [] = Voltar();
		System.out.println(Arrays.toString(a));
	}
	
	public static String[] Voltar() {
		int brunomagro[] = {0, 10, 1500, 2, 45, 15, 2000};
		String brunogordo[] = new String[brunomagro.length];

		for (int i = 0; i < brunomagro.length; i++) {
			switch (brunomagro[i]) {
			case RetaID:
				int value = brunomagro[i + 1];
				int sleep = brunomagro[i + 2];
				
				for (int j = brunogordo.length - 1; j >= 0; j--) {
					if (brunogordo[j] == null) {
						brunogordo[j] = String.valueOf(sleep);
						brunogordo[j - 1] = String.valueOf(value);
						brunogordo[j - 2] = String.valueOf(RetaID);
						break;
					}
				}
				// id - valor - sleep

				i += 2;
				break;
			case RetaguardaID:
				int value1 = brunomagro[i + 1];
				int sleep1 = brunomagro[i + 2];
				
				for (int j = brunogordo.length - 1; j >= 0; j--) {
					if (brunogordo[j] == null) {
						brunogordo[j] = String.valueOf(sleep1);
						brunogordo[j - 1] = String.valueOf(value1);
						brunogordo[j - 2] = String.valueOf(RetaguardaID);
						break;
					}
				}
				i += 2;
				break;
			case CurvarEsqID:
				int Raio = brunomagro[i + 1];
				int Angulo = brunomagro[i + 2];
				int sleep2 = brunomagro[i + 3];
				
				for (int j = brunogordo.length - 1; j >= 0; j--) {
					if (brunogordo[j] == null) {
						brunogordo[j] = String.valueOf(sleep2);
						brunogordo[j - 1] = String.valueOf(Angulo);
						brunogordo[j - 2] = String.valueOf(Raio);
						brunogordo[j - 3] = String.valueOf(CurvarEsqID);
						break;
					}
				}

				i += 3;
				break;
			case CurvarDirID:
				int Raio1 = brunomagro[i + 1];
				int Angulo1 = brunomagro[i + 2];
				int sleep3 = brunomagro[i + 3];
				
				for (int j = brunogordo.length - 1; j >= 0; j--) {
					if (brunogordo[j] == null) {
						brunogordo[j] = String.valueOf(sleep3);
						brunogordo[j - 1] = String.valueOf(Angulo1);
						brunogordo[j - 2] = String.valueOf(Raio1);
						brunogordo[j - 3] = String.valueOf(CurvarDirID);
						break;
					}
				}
				i += 3;
				break;
			case PararID:
				boolean bool = brunomagro[i + 1] == 1 ? true : false;
				int sleep4 = brunomagro[i + 2];
				
				for (int j = brunogordo.length - 1; j >= 0; j--) {
					if (brunogordo[j] == null) {
						brunogordo[j] = String.valueOf(sleep4);
						brunogordo[j - 1] = String.valueOf(bool);
						brunogordo[j - 2] = String.valueOf(PararID);
						break;
					}
				}
				i += 2;
				break;
			}
		}
		return brunogordo;
	}

	public int[] convertArray(String value[]) {
		int[] intvalue = new int[value.length];
		for (int i = 0; i < intvalue.length; i++)
			intvalue[i] = Integer.parseInt(value[i]);

		return intvalue;
	}

}
