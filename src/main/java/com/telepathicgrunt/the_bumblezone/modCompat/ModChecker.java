package com.telepathicgrunt.the_bumblezone.modCompat;

import com.telepathicgrunt.the_bumblezone.Bumblezone;
import net.minecraftforge.fml.ModList;
import org.apache.logging.log4j.Level;

public class ModChecker
{
    public static boolean potionOfBeesPresent = false;
	public static boolean productiveBeesPresent = false;
	public static boolean carrierBeesPresent = false;
	public static boolean resourcefulBeesPresent = false;


    public static void setupModCompat() {
		Bumblezone.LOGGER.log(Level.WARN, "Starting compat");
		Bumblezone.LOGGER.log(Level.WARN, "Show all mods: "+ModList.get().getMods().stream().map(e -> e.getModId() + ", ").reduce("", String::concat));

		Bumblezone.LOGGER.log(Level.WARN, "Direct resourcefulbees check: " + ModList.get().isLoaded("resourcefulbees"));
		loadupModCompat("potionofbees", PotionOfBeesCompat::setupPotionOfBees);
		loadupModCompat("carrierbees", CarrierBeesCompat::setupProductiveBees);
		loadupModCompat("productivebees", ProductiveBeesCompat::setupProductiveBees);
		loadupModCompat("resourcefulbees", ResourcefulBeesCompat::setupResourcefulBees);
    }

    private static void loadupModCompat(String modid, Runnable runnable){
		try {
			Bumblezone.LOGGER.log(Level.WARN, "Before modlist check");
			if (ModList.get().isLoaded(modid)) {
				Bumblezone.LOGGER.log(Level.WARN, "After modlist check");
				Bumblezone.LOGGER.log(Level.WARN, "Runnable: "+runnable+" "+ runnable.toString());
				runnable.run();
				Bumblezone.LOGGER.log(Level.WARN, "After runnable");
			}
		}
		catch (Exception e) {
			printErrorToLogs(modid);
			e.printStackTrace();
		}
	}


    private static void printErrorToLogs(String currentModID) {
		Bumblezone.LOGGER.log(Level.INFO, "------------------------------------------------NOTICE-------------------------------------------------------------------------");
		Bumblezone.LOGGER.log(Level.INFO, " ");
		Bumblezone.LOGGER.log(Level.INFO, "ERROR: Something broke when trying to add mod compatibility with" + currentModID + ". Please let The Bumblezone developer (TelepathicGrunt) know about this!");
		Bumblezone.LOGGER.log(Level.INFO, " ");
		Bumblezone.LOGGER.log(Level.INFO, "------------------------------------------------NOTICE-------------------------------------------------------------------------");
    }

}
