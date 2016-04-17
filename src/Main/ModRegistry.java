package Main;
import java.io.IOException;


public class ModRegistry {
	public static void main (String[] args) throws IOException	{
		//ForgeMod.addSword("Testing");
		ForgeMod.addBlock("Testing");
		//ForgeMod.addBow("Testing");
		ForgeMod.addMaterial("BANANA", 3, 3000, 15F, 150F, 200);
		ForgeMod.addSword("MatSword","BANANA");
	}
}
