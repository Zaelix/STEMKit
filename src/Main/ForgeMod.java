package Main;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ForgeMod{
	static Path campPath;
	static Path mainRegistry;
	static Path itemRegistry;
	static Path blockRegistry;
	static String eol = System.getProperty("line.separator");
	
	// Defines the content of a new Sword File for use in the writeFile() method
	public static String createNewSwordContent(String name){
		String newSword = "package com.camp.item;" + eol + "import net.minecraft.item.Item.ToolMaterial;" + eol +  
				"import net.minecraft.item.ItemSword;" + eol + "import com.camp.lib.Strings;" + eol + 
				"import com.camp.main.MainRegistry;" + eol + "import net.minecraft.creativetab.CreativeTabs;" + eol + 
				"public class " + name + "Sword extends ItemSword{" + eol + "	public " + name + "Sword(ToolMaterial material){" + eol + 
				"		super(material);" + eol + "		this.setUnlocalizedName(\"" + name +"\");" + eol + 
				"		this.setCreativeTab(CreativeTabs.tabCombat);" + eol + "		this.setMaxStackSize(1);" + eol + 
				"		this.setTextureName(Strings.MODID + \":\" + \"" + name + "\");" + eol + "	}" + eol + "}";
		return newSword;
	}
	
	// Defines the content of a new Block File for use in the writeFile() method
	public static String createNewBlockContent(String name){
		String newBlock = "package com.camp.block;" + eol + "import com.camp.lib.Strings;" + eol + 
				"import net.minecraft.block.Block;" + eol + "import net.minecraft.block.material.Material;" + eol + 
				"import net.minecraft.creativetab.CreativeTabs;" + eol + "public class " + name + "Block extends Block{" + eol + 
				"	protected " + name + "Block(Material p_i45394_1_){" + eol + "		super(p_i45394_1_);" + eol + 
				"		this.setBlockName(\"" + name + "Block\");" + eol + "		this.setCreativeTab(CreativeTabs.tabBlock);" + eol + 
				"		this.setBlockTextureName(Strings.MODID + \":\" + \"" + name + "_block\");" + eol + "	}" + eol + "}";
		return newBlock;
	}
	
	// Defines the content of a new Bow File for use in the writeFile() method
	public static String createNewBowContent(String name){
		String newBow = "package com.camp.item;" + eol + eol + "import com.camp.lib.Strings;" + eol + 
				"import cpw.mods.fml.relauncher.Side;" + eol + "import cpw.mods.fml.relauncher.SideOnly;" + eol + 
				"import net.minecraft.client.renderer.texture.IIconRegister;" + eol + 
				"import net.minecraft.creativetab.CreativeTabs;" + eol + 
				"import net.minecraft.entity.player.EntityPlayer;" + eol + "import net.minecraft.item.ItemBow;" + eol + 
				"import net.minecraft.item.ItemStack;" + eol + "import net.minecraft.util.IIcon;" + eol + eol +
				"public class " + name + "Bow extends ItemBow{" + eol + 
				"public static final String[] iconNameArray = new String[] {\"pulling_0\", \"pulling_1\", \"pulling_2\"};" + eol + 
				"   @SideOnly(Side.CLIENT)" + eol + "	private IIcon[] iconArray;" + eol + 
				"    private static final String iconString = \"" + name + "Bow\";" + eol + eol + "    public " + name + "Bow()" + eol + 
				"	{" + eol + "    	this.setUnlocalizedName(\"" + name + "Bow\");" + eol + 
				"        this.maxStackSize = 1;" + eol + "        this.setMaxDamage(3840);" + eol + 
				"        this.setCreativeTab(CreativeTabs.tabCombat);" + eol + "    }" + eol + eol + 
				"    @SideOnly(Side.CLIENT)" + eol + "    public void registerIcons(IIconRegister icon)" + eol + 
				"    {" + eol + "        this.itemIcon = icon.registerIcon(Strings.MODID + \":\" + \"" + name + "Bow\" + \"_standby\");" + eol + 
				"        this.iconArray = new IIcon[iconNameArray.length];" + eol + 
				"        for (int i = 0; i < this.iconArray.length; ++i)" + eol + "        {" + eol + 
				"            this.iconArray[i] = icon.registerIcon(Strings.MODID + \":\" + \"" + name + "Bow\" + \"_\" + iconNameArray[i]);" + eol + 
				"        }" + eol + "    }" + eol + eol + "    @Override" + eol + "    @SideOnly(Side.CLIENT)" + eol + 
				"    public IIcon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining) {" + eol + 
				"        if (usingItem == null) { return itemIcon; }" + eol + "        int ticksInUse = stack.getMaxItemUseDuration() - useRemaining;" + eol + 
				"        if (ticksInUse > 18) {" + eol + "              return iconArray[2];" + eol + "        } else if (ticksInUse > 14) {" + eol + 
				"            return iconArray[1];" + eol + "        } else if (ticksInUse > 0) {" + eol + "            return iconArray[0];" + eol + 
				"        } else {" + eol + "            return itemIcon;" + eol + "        }" + eol + "    }" + eol + "};";
		return newBow;
	}
	
	// Finds the location with a given string in the given file, and creates the content to overwrite the existing file with.
	public static Object[] findInsertingSpace(Path myFile, String name, String searchString, String insertString, int num, String fileContent) throws IOException{
		int number_of_lines = 0;
		
		InputStream in = Files.newInputStream(myFile);
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		
		String lineContents = "";
		lineContents = reader.readLine();
		while (lineContents != null)
		{
			if(number_of_lines >= num){
				fileContent = fileContent + lineContents + "\n";
			}
			if (lineContents.contains("// " + searchString + " --")){
				//System.out.println(searchString + " found!");
				fileContent = fileContent + insertString + "\n";
				break;
			}
			number_of_lines++;
			lineContents = reader.readLine();
		}
		reader.close();
		Object[] values = new Object[]{fileContent, number_of_lines+1};
		return values;
	}
	
	// Finishes reading the file's contents and adding them to the new content string before it overwrites the old file.
	public static String finishRead(Path myFile, int num, String fileContent) throws IOException{		
		int number_of_lines = 0;
		InputStream in = Files.newInputStream(myFile);
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		
		String lineContents = "";
		lineContents = reader.readLine();
		while (lineContents != null)
		{
			if(number_of_lines >= num){
				fileContent = fileContent + lineContents + "\n";
			}
			number_of_lines++;
			lineContents = reader.readLine();
		}
		reader.close();
		return fileContent;
	}
	
	// Determines the file paths to be used by the rest of the program.
	public static Path findDirectory() throws FileNotFoundException{
		Path myPath = null;
		
		File testDrive = new File("E:/Desktop Stuff/Programming/ForgeMod Test Folder/");
		Path testPath = testDrive.toPath();
		
		for (char letter = 'D'; letter <= 'Z'; letter++){
			File drive = new File(letter + ":/src/main/java/com/camp/");
			if (drive.isDirectory()){
				System.out.println("Found directory in drive " + letter + ":/");
				myPath = drive.toPath();
				if(!checkFiles(letter)){
					System.out.println("Not all files accounted for! Exiting program.");
					System.exit(1);
				}
				break;
			}
		}
		
		if (myPath != null){
			//System.out.println(myPath);
		}
		else{
			System.out.println("Yikes! All file paths are invalid! Reverting to testPath.");
			if (testDrive.isDirectory()){
				myPath = testPath;
			}
			else{
				throw new FileNotFoundException("Test Path Not Valid! Exiting.");
			}
		}
		
		if (myPath == testPath){
			mainRegistry = Paths.get(myPath + "/MainRegistry.java");
			itemRegistry = Paths.get(myPath + "/ItemRegistry.java");
			blockRegistry = Paths.get(myPath + "/BlockRegistry.java");
		}
		else{
			mainRegistry = Paths.get(myPath + "/main/MainRegistry.java");
			itemRegistry = Paths.get(myPath + "/item/ItemRegistry.java");
			blockRegistry = Paths.get(myPath + "/block/BlockRegistry.java");
		}
		campPath = myPath;
		return myPath;
	}
	
	// Checks whether all the base mod files exist in the given directory
	public static boolean checkFiles(char drive){
		boolean result = true;
		File checkBlock = new File(drive + ":/src/main/java/com/camp/block/BlockRegistry.java");
		File checkItem = new File(drive + ":/src/main/java/com/camp/item/ItemRegistry.java");
		File checkMain = new File(drive + ":/src/main/java/com/camp/main/MainRegistry.java");
		File checkRecipe = new File(drive + ":/src/main/java/com/camp/main/RecipeRegistry.java");
		if (!checkBlock.isFile()){
			System.out.println("BlockRegistry not found!");
			result = false;
		}
		if (!checkItem.isFile()){
			System.out.println("ItemRegistry not found!");
			result = false;
		}
		if (!checkMain.isFile()){
			System.out.println("MainRegistry not found!");
			result = false;
		}
		if (!checkRecipe.isFile()){
			System.out.println("RecipeRegistry not found!");
			result = false;
		}
		return result;
	}
	
	// Checks whether the given string has already been added to the file, to avoid duplication of object declaration, initialization, and registry.
	public static boolean isRedundant(Path myFile, String searchString) throws IOException{
		InputStream in = Files.newInputStream(myFile);
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		
		String lineContents = "";
		lineContents = reader.readLine();
		while (lineContents != null)
		{
			if (lineContents.contains(searchString)){
				//System.out.println("Search String found in \"" + myFile + "\": " + searchString);
				reader.close();
				return true;
			}
			lineContents = reader.readLine();
		}
		reader.close();
		return false;
	}
	
	public static void addBlock(String name) throws IOException{
		addObject(name, "Block", "Block", " ");
	}

	public static void addSword(String name) throws IOException{
		addObject(name, "Item", "Sword", " ");
	}
	
	public static void addSword(String name, String materialName) throws IOException{
		addObject(name, "Item", "Sword", materialName);
	}
	
	public static void addBow(String name) throws IOException{
		addObject(name, "Item", "Bow", " ");
	}
	
	// Adds the necessary material declaration line to the MainRegistry, if it doesn't already exist.
	public static void addMaterial(String name, int harvestLevel, int durability, float harvestSpeed, float damage, int enchantability) throws IOException{
		findDirectory();
		Path typeFile = mainRegistry;
		
		String decSearchString = "Material Declaration Space";
		String decInsertString = "	public static final Item.ToolMaterial " + name + " = EnumHelper.addToolMaterial(\"" + name + "\", " + harvestLevel + 
				", " + durability + ", " + harvestSpeed + ", " + damage + ", " + enchantability + ");";
		
		Object[] temp = findInsertingSpace(typeFile, name, decSearchString, decInsertString, 0, "");
		String fileContent = (String)temp[0];
		int num = (int)temp[1];
		
		fileContent = finishRead(typeFile, num, fileContent);
		
		if(isRedundant(typeFile, decInsertString)){
			System.out.println("Material Registration already exists, skipping Main Registry rewrite");
		}
		else{
			System.out.println("Updating Main Registry...");
			System.out.println("Opening file: " + typeFile.toString());
			writeFile(typeFile, fileContent);
		}
	}
	
	// Adds the necessary declarations, initializations, and registrations for all supported object types. This currently includes: swords, bows, and blocks
	public static void addObject(String name, String category, String type, String material) throws IOException{
		findDirectory();
		Path typeFile;
		String initInsertEnd;

		if (material == " "){
			material = "WAFFLETOOL";
		}
		
		if(type == "Block"){
			typeFile = blockRegistry;
			initInsertEnd = "(Material.rock);";
		}
		else if(type == "Sword"){
			typeFile = itemRegistry;
			initInsertEnd = "(MainRegistry."+material+");";
		}
		else if(type == "Bow"){
			typeFile = itemRegistry;
			initInsertEnd = "();";
		}
		else{
			typeFile = mainRegistry;
			initInsertEnd = "";
		}
		
		// Block Strings
		String decSearchString = category + " Declaration Space";
		String decInsertString = "	public static " + category + " " + name.toLowerCase() + type + ";";
		
		String initSearchString = category + " Initialization Space";
		String initInsertString = "		"+name.toLowerCase() + type + " = new " + name + type + initInsertEnd;
		
		String regSearchString = category + " Registration Space";
		String regInsertString = "		GameRegistry.register" + category + "(" + name.toLowerCase() + type + ", " + name.toLowerCase() + type + ".getUnlocalizedName());";
		
		Object[] temp = findInsertingSpace(typeFile, name, decSearchString, decInsertString, 0, "");
		String fileContent = (String)temp[0];
		int num = (int)temp[1];
		
		//System.out.println("Finding " + blockInitSearchString + "...");
		temp = findInsertingSpace(typeFile, name, initSearchString, initInsertString, num, fileContent);
		fileContent = (String)temp[0];
		num = (int)temp[1];
		
		//System.out.println("Finding " + blockRegSearchString + "...");
		temp = findInsertingSpace(typeFile, name, regSearchString, regInsertString, num, fileContent);
		fileContent = (String)temp[0];
		num = (int)temp[1];
		
		fileContent = finishRead(typeFile, num, fileContent);
		
		if(isRedundant(typeFile, decInsertString) && 
				isRedundant(typeFile, initInsertString) &&
				isRedundant(typeFile, regInsertString)){
			System.out.println(category + " Registration already exists, skipping " + category + "Registry rewrite");
		}
		else{
			System.out.println("Updating " + category + "Registry...");
			System.out.println("Opening file: " + typeFile.toString());
			writeFile(typeFile, fileContent);
		}
		System.out.println("Creating " + type + " Class file...");
		if (type == "Block"){
			writeFile(Paths.get(campPath + "/" + category.toLowerCase() + "/" + name + type + ".java"), createNewBlockContent(name));
		}
		else if (type == "Sword"){
			writeFile(Paths.get(campPath + "/" + category.toLowerCase() + "/" + name + type + ".java"), createNewSwordContent(name));
		}
		else if (type == "Bow"){
			writeFile(Paths.get(campPath + "/" + category.toLowerCase() + "/" + name + type + ".java"), createNewBowContent(name));
		}
	}
	
	// Writes the given content string to the specified file. This overwrites any existing file with the new content.
	public static void writeFile(Path myFile, String fileContent) throws IOException{
		OutputStream out = Files.newOutputStream(myFile);
		
		byte data[] = fileContent.getBytes();
		
		try {
			out.write(data);
		} catch (IOException e) {
			System.out.println("IO Exception");
			e.printStackTrace();
		}
	}
}
