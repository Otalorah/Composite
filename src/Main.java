import io.Input;
import io.Output;
import component.*;
import model.Character;
import factory.WebFactory;
import factory.GUIFactory;
import factory.ConsoleFactory;
import factory.InterfaceFactory;
import builder.CharacterDirector;
import builder.RPGCharacterBuilder;

import java.util.Map;

public class Main {

   // ---------------------------------------------------------------
   // Abstract Factory field — swapped at runtime based on user choice
   // ---------------------------------------------------------------
   private static InterfaceFactory interfaceFactory;

   public static void main(String[] args) {

      // --- Abstract Factory + Adapter: choose I/O interface ---
      System.out.println("=== I/O Interface Selection ===");
      interfaceFactory = chooseFactory();

      Input  input  = interfaceFactory.createInput();
      Output output = interfaceFactory.createOutput();

      output.show("Enter a number to echo back through the selected interface:");
      String value = input.read();
      output.show("You entered: " + value);

      RPGCharacterBuilder builder = new RPGCharacterBuilder();
      CharacterDirector director = new CharacterDirector(builder);

      // --- Builder Pattern: create predefined characters ---
      output.show("=== Builder Pattern Demo ===");

      Character tank = director.createTank("Arthas");
      output.show("Tank created: " + tank);

      Character rogue = director.createRogue("Zara");
      output.show("Rogue created: " + rogue);

      Character mage = director.createCharacter("Gandalf", "Mage");
      output.show("Mage created: " + mage);

      // --- Composite Pattern: build an EquipmentSet ---
      output.show("\n=== Composite Pattern Demo ===");

      EquipmentSet dragonSet = new EquipmentSet(
            "Dragon Set",
            Map.of("attack", 20, "health", 40),
            3);

      dragonSet.addComponent(new Equipment("Dragon Helm", Map.of("defense", 15), "head"));
      dragonSet.addComponent(new Equipment("Dragon Chest", Map.of("defense", 25, "health", 20), "chest"));
      dragonSet.addComponent(new Equipment("Dragon Greaves", Map.of("defense", 10, "speed", 5), "legs"));

      output.show("Set complete: " + dragonSet.isComplete());
      output.show("Dragon Set total stats: " + dragonSet.getStats());

      Character warrior = builder
            .setBasicInfo("Siegfried", "Warrior")
            .setLevel(10)
            .setBaseStats(200, 40, 35, 25, 18)
            .addEquipment(dragonSet)
            .addSkill(new Skill("Sword Slash", 50, 10, "physical"))
            .build();

      output.show("Warrior total stats: " + warrior.getTotalStats());

      // --- Prototype Pattern: clone a character ---
      output.show("\n=== Prototype Pattern Demo ===");

      Character warriorClone = warrior.clone();
      warriorClone.setLevel(11);
      warriorClone.setState("name=Siegfried II");

      output.show("Original warrior key : " + warrior.getKey());
      output.show("Cloned  warrior key  : " + warriorClone.getKey());
      output.show("Cloned  warrior stats: " + warriorClone.getTotalStats());
   }

   // ---------------------------------------------------------------
   // Factory selection helper
   // ---------------------------------------------------------------

   /**
    * Prompts the user to choose a UI technology and returns the matching
    * concrete {@link InterfaceFactory}.
    *
    * <pre>
    *  1 → ConsoleFactory  (reads/writes on System.in / System.out)
    *  2 → GUIFactory      (Swing JOptionPane dialogs)
    *  3 → WebFactory      (one-shot HTTP server on localhost)
    * </pre>
    */
   private static InterfaceFactory chooseFactory() {
      // Use the shared scanner — never create a second Scanner on System.in
      java.util.Scanner scanner = io.ConsoleScanner.get();
      System.out.println("Choose the I/O interface:");
      System.out.println("  1 - Console");
      System.out.println("  2 - GUI (Swing)");
      System.out.println("  3 - Web (HTTP)");
      System.out.print("Your choice [1-3]: ");

      int choice = 0;
      if (scanner.hasNextInt()) {
            choice = scanner.nextInt();
      }

      return switch (choice) {
         case 2  -> {
            System.out.println("→ Using GUIFactory (Swing dialogs)");
            yield new GUIFactory();
         }
         case 3  -> {
            System.out.println("→ Using WebFactory (HTTP on localhost:8080?value=Int (Input) / :8081 (Reload to see Output))");
            yield new WebFactory();
         }
         default -> {
            System.out.println("→ Using ConsoleFactory (stdin / stdout)");
            yield new ConsoleFactory();
         }
      };

   }
}
