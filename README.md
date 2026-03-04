# RPG Character System

Proyecto Java que modela un sistema de personajes RPG aplicando los patrones de diseño **Composite**, **Builder** y **Prototype**.

## Patrones de diseño implementados

### Composite
Permite construir jerarquías de componentes de equipo tratando objetos individuales y grupos de la misma forma.

- `CharacterComponent` — componente abstracto base
- `Equipment` / `Skill` — hojas (leaf nodes)
- `CompositeComponent` — nodo compuesto genérico
- `EquipmentSet` — compuesto especializado que aplica un bonus cuando el set está completo

### Builder
Separa la construcción de un `Character` de su representación, permitiendo crear diferentes tipos de personaje con el mismo proceso.

- `CharacterBuilder` — interfaz fluent con pasos de construcción
- `RPGCharacterBuilder` — implementación concreta; llama a `reset()` automáticamente tras cada `build()`
- `CharacterDirector` — encapsula recetas predefinidas: `createTank()`, `createRogue()`, `createCharacter()`

### Prototype
Permite clonar personajes y componentes de forma profunda (deep copy), evitando compartir estado entre el original y la copia.

- `Prototype<T>` — interfaz genérica con `clone()`
- Implementado en `BaseStats`, `Character`, `Equipment`, `Skill`, `CompositeComponent`, `EquipmentSet`

## Estructura del proyecto

```
Composite/
├── pom.xml             ← Maven build file (Java 17, ejecutable JAR)
├── build-run.bat       ← compilar y ejecutar en Windows (doble clic o CMD)
├── build-run.sh        ← compilar y ejecutar en Linux / macOS
├── src/
│   ├── Main.java
│   ├── prototype/
│   │   └── Prototype.java
│   ├── model/
│   │   ├── BaseStats.java
│   │   └── Character.java
│   ├── component/
│   │   ├── CharacterComponent.java
│   │   ├── Skill.java
│   │   ├── Equipment.java
│   │   ├── CompositeComponent.java
│   │   └── EquipmentSet.java
│   ├── builder/
│   │   ├── CharacterBuilder.java
│   │   ├── RPGCharacterBuilder.java
│   │   └── CharacterDirector.java
│   ├── io/                        ← interfaces y clases concretas de I/O
│   │   ├── Input.java             (interfaz objetivo — Abstract Factory product)
│   │   ├── Output.java            (interfaz objetivo — Abstract Factory product)
│   │   ├── ConsoleScanner.java    (Scanner compartido para System.in)
│   │   ├── ConsoleInput/Output.java
│   │   ├── GUIInput/Output.java   (Swing JOptionPane)
│   │   └── WebInput/Output.java   (HTTP en localhost:8080 / :8081)
│   ├── adapter/                   ← Adapter Pattern (int ↔ String)
│   │   ├── AdapterConsoleInput/Output.java
│   │   ├── AdapterGUIInput/Output.java
│   │   └── AdapterWebInput/Output.java
│   └── factory/                   ← Abstract Factory Pattern
│       ├── InterfaceFactory.java
│       ├── ConsoleFactory.java
│       ├── GUIFactory.java
│       └── WebFactory.java
└── out/   ← clases compiladas (generado)
```

## Requisitos

- Java 17 o superior (se usa _switch expressions_ y `var`)

## Compilar y ejecutar

### Opción 1 — Maven (recomendado con IDE)

Requiere **Maven 3.6+** y **JDK 17+**.

```bash
# Compilar y empaquetar en un JAR ejecutable
mvn package

# Ejecutar el JAR generado
java -jar target/rpg-character-system.jar
```

Para **importar en un IDE**:
- **IntelliJ IDEA / Eclipse / NetBeans**: *File → Open* (o *Import*) → seleccionar la carpeta `Composite/` — el IDE detecta el `pom.xml` automáticamente y configura todo.

---

### Opción 2 — Scripts listos para usar (sin IDE ni Maven)

**Windows** — doble clic en `build-run.bat`, o desde CMD/PowerShell:
```bat
build-run.bat
```

**Linux / macOS** — desde la terminal:
```bash
chmod +x build-run.sh
./build-run.sh
```

### Opción 2 — Comandos manuales

Desde la raíz del proyecto (`Composite/`):

```powershell
# Windows PowerShell
javac -d out -sourcepath src (Get-ChildItem -Recurse src -Filter "*.java").FullName
java -cp out Main
```

```bash
# Linux / macOS
find src -name "*.java" | xargs javac -d out -sourcepath src
java -cp out Main
```

## Salida esperada

```
=== Builder Pattern Demo ===
Tank created:  Character{name='Arthas',  class='Tank',  level=1, ...}
Rogue created: Character{name='Zara',    class='Rogue', level=1, ...}
Mage created:  Character{name='Gandalf', class='Mage',  level=1, ...}

=== Composite Pattern Demo ===
Set complete: true
Dragon Set total stats: {defense=50, attack=20, health=60, speed=5}
Warrior total stats:    {mana=40, defense=75, attack=55, health=260, speed=23}

=== Prototype Pattern Demo ===
Original warrior key: Siegfried_Warrior_10
Cloned  warrior key:  Siegfried II_Warrior_11
Cloned  warrior stats: {mana=40, defense=75, attack=55, health=260, speed=23}
```

## Diagrama de clases

La estructura sigue el siguiente diagrama UML:

- `Main` usa `CharacterDirector` y `RPGCharacterBuilder`
- `CharacterDirector` depende de la interfaz `CharacterBuilder`
- `RPGCharacterBuilder` implementa `CharacterBuilder` y produce `Character`
- `Character` contiene un `CompositeComponent` (equipo) y una lista de `CharacterComponent` (skills)
- `CompositeComponent` y `EquipmentSet` forman el árbol Composite sobre `CharacterComponent`
- Todos los nodos implementan `Prototype<T>` para clonación profunda
