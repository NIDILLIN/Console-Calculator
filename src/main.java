import java.util.*;

class main
{
	static String input;
	static int opIndex;
	static String operator;
	static Scanner in = new Scanner(System.in);
	static boolean notEnd = true;
	static String[] chislo_1;
	static String[] chislo_2;
	static long mainResult;

	public static void main(String[] args) {
		System.out.println("ЗДАРОВА!");

		begin();

		System.out.println("ДОСВИДОС!");
	}




	static void begin()
	{
		do
		{
			//welcome msg and (yes or not) and commands

			input = in.nextLine();

			if ( input.equalsIgnoreCase("end") ) end();

			if ( input.equalsIgnoreCase("commands") ) lsComms();

			//welcome msg and (yes or not) and commands

			calcCicle();
		}
		while(notEnd);
	}

	static void calcCicle()
	{
		String line = input;
		String[] lineSub = new String[line.length()];
		String[] operators = {"+", "-", "/", "*", "^"};
		boolean go = false;
		// НАХОЖДЕНИЕ ОПЕРАТОРА, ЧИСЛА_1 И ЧИСЛА_2

		for (int i = 0; i < line.length(); i++)
			lineSub[i] = line.substring(i, i + 1);

		// узнаем индекс строки с оператором 
		for (int i = 0; i < line.length(); i++)
			for (int it = 0; it < operators.length; it++)
				if ( lineSub[i].equals(operators[it]) )
				{
					go = true;
					operator = operators[it];
					opIndex = i;
					// Зная индекс оператора в массиве lineSub, узнаем длину первого и второго числа
					chislo_1 = new String[i];
					chislo_2 = new String[(line.length() - i) - 1];
				}


		if (go)
		{
			boolean opNotFirstSym = true;

			if ( opIndex == 0 ) opNotFirstSym = false;

			calcTry(line, lineSub, opIndex, opNotFirstSym);
			
		}



	}

	static void lsComms()
	{
		String[] commands = {"+", "-", "/", "*", "^"};
		for (String comm : commands)
		{
			System.out.println(comm);	
		}
		
	}

	static void calcTry(String line, String[] lineSub, int opIndex, boolean opNotFirstSym)
	{
		long chislo_1_Int = 0;
		String chislo_1_String = "";
		Calc cl;
		// Заполняем массивы числами, по данным индексам
		if (opNotFirstSym)
		{
			for (int j = 0; j < chislo_1.length; j++)
			chislo_1[j] = lineSub[j];
		}
		// Начиная со следующего знака после оператора
		for (int j = opIndex + 1, a = 0; j < line.length(); j++, a++)
			chislo_2[a] = lineSub[j];

		// СОЗДАНИЕ СТРОКИ И ПЕРЕВОД В ЧИСЛО
		
		if (opNotFirstSym) { chislo_1_String = strBuild(chislo_1); }
		String chislo_2_String = strBuild(chislo_2);

		if (opNotFirstSym) { chislo_1_Int = Long.parseLong (chislo_1_String); }
		long chislo_2_Int = Long.parseLong (chislo_2_String);
		
		// ВЫЧИСЛЕНИЯ

		try
		{
			if (opNotFirstSym) { cl = new Calc(chislo_1_Int, chislo_2_Int); }
			else { cl = new Calc(mainResult, chislo_2_Int); }

			switch (operator)
			{
				case "+":
					cl.plus();
				break;

				case "-":
					cl.minus();
				break;

				case "*":
					cl.multiply();
				break;

				case "/":
					cl.divide();
				break;

				case "^":
					cl.quadr(chislo_2_Int);
				break;
			}
			System.out.println(cl.getRes());
			mainResult = cl.getRes();

		} catch(NumberFormatException nEx)
		{
			System.out.println(nEx.getMessage());
		}

	}

	static void end()
	{
		notEnd = false;
	}

	static String strBuild(String[] arg)
	{
		StringBuilder strBuilder = new StringBuilder();	

		for (int i = 0; i < arg.length; i++) 
		{
			strBuilder.append(arg[i]);	
		}

		return strBuilder.toString();
	}
}

class Calc
{
	private long a, b, result; // Операнды

	public Calc(long arg1, long arg2)
	{
		this.a = arg1;
		this.b = arg2;
	}

	long getA()
	{
		return this.a;
	}

	long getB()
	{
		return this.b;
	}

	long getRes()
	{
		return this.result;
	}

	void setA(long arg)
	{
		this.a = arg;
	}

	void setB(long arg)
	{
		this.b = arg;
	}


	void plus()
	{
		this.result = this.a + this.b;
	}

	void minus()
	{
		this.result = this.a - this.b;
	}

	void divide()
	{
		this.result = this.a / this.b;
	}

	void multiply()
	{
		this.result = this.a * this.b;
	}

	void quadr(long count)
	{
		for (int i = 0; i < count; i++)
		{
			this.result = this.a * this.a;
		}
	}
}