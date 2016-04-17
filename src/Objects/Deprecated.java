package Objects;

public class Deprecated {
	/*
	public static void addBlockDEP(String name) throws IOException{
		Path myPath = findFiles();

		// Block Strings
		String blockDecSearchString = "Block Declaration Space";
		String blockDecInsertString = "	public static Block " + name + "_Block;";
		
		String blockInitSearchString = "Block Initialization Space";
		String blockInitInsertString = "		"+name+"_Block = new " + name + "Block(Material.rock);";
		
		String blockRegSearchString = "Block Registration Space";
		String blockRegInsertString = "		GameRegistry.registerBlock(" + name + "_Block, " + name + "Block.getUnlocalizedName());";
		
		Object[] temp = findInsertingSpace(blockRegistry, name, blockDecSearchString, blockDecInsertString, 0, "");
		String fileContent = (String)temp[0];
		int num = (int)temp[1];
		
		//System.out.println("Finding " + blockInitSearchString + "...");
		temp = findInsertingSpace(blockRegistry, name, blockInitSearchString, blockInitInsertString, num, fileContent);
		fileContent = (String)temp[0];
		num = (int)temp[1];
		
		//System.out.println("Finding " + blockRegSearchString + "...");
		temp = findInsertingSpace(blockRegistry, name, blockRegSearchString, blockRegInsertString, num, fileContent);
		fileContent = (String)temp[0];
		num = (int)temp[1];
		
		fileContent = finishRead(blockRegistry, num, fileContent);
		
		//System.out.println("Writing to File \"" + blockRegistry + "\"");
		if(isRedundant(blockRegistry, blockDecInsertString) && 
				isRedundant(blockRegistry, blockInitInsertString) &&
				isRedundant(blockRegistry, blockRegInsertString)){
			System.out.println("Block Registration already exists, skipping BlockRegistry rewrite");
		}
		else{
			System.out.println("Updating BlockRegistry...");
			writeFile(blockRegistry, fileContent);
		}
		System.out.println("Creating Block Class file...");
		writeFile(Paths.get(campPath + "/block/" + name + "Block.java"), createNewBlockContent(name));
	}

	public static void addSwordDEP(String name) throws IOException{
		Path myPath = findFiles();
		
		// Sword Strings
		String swordDecSearchString = "Sword Declaration Space";
		String swordDecInsertString = "	public static Item " + name + "_Sword;";
		
		String swordInitSearchString = "Sword Initialization Space";
		String swordInitInsertString = "		"+name+"_Sword = new " + name + "Sword(MainRegistry.WAFFLETOOL);";
		
		String swordRegSearchString = "Sword Registration Space";
		String swordRegInsertString = "		GameRegistry.registerItem(" + name + "_Sword, " + name + "Sword.getUnlocalizedName());";
		
		//System.out.println("Finding " + swordDecSearchString + "...");
		Object[] temp = findInsertingSpace(itemRegistry, name, swordDecSearchString, swordDecInsertString, 0, "");
		String fileContent = (String)temp[0];
		int num = (int)temp[1];
		
		//System.out.println("Finding " + swordInitSearchString + "...");
		temp = findInsertingSpace(itemRegistry, name, swordInitSearchString, swordInitInsertString, num, fileContent);
		fileContent = (String)temp[0];
		num = (int)temp[1];
		
		//System.out.println("Finding " + swordRegSearchString + "...");
		temp = findInsertingSpace(itemRegistry, name, swordRegSearchString, swordRegInsertString, num, fileContent);
		fileContent = (String)temp[0];
		num = (int)temp[1];
		
		fileContent = finishRead(itemRegistry, num, fileContent);
		
		//System.out.println("Writing to File \"" + itemRegistry + "\"");
		if(isRedundant(itemRegistry, swordDecInsertString) && 
				isRedundant(itemRegistry, swordInitInsertString) &&
				isRedundant(itemRegistry, swordRegInsertString)){
			System.out.println("Item Registration already exists, skipping ItemRegistry rewrite");
		}
		else{
			System.out.println("Updating ItemRegistry...");
			writeFile(itemRegistry, fileContent);
		}
		System.out.println("Creating Sword Class file...");
		writeFile(Paths.get(campPath + "/item/" + name + "Sword.java"), createNewSwordContent(name));
	}
	*/
}
