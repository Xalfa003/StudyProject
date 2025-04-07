import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    enum ArrayMethod {
        RANDOM,
        MANUAL,
        UNKNOWN,
        ADD,
        EXIT,
        MAX,
        MIN,
        REMOVE,
        DELETE
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] array = new int[0]; 
        boolean createdArray = false;
    
        while (true) {
            ArrayMethod method;
            if (!createdArray) {
                method = startMenu(scanner); 
            } else {
                method = editMenu(scanner); 
            }
    
            switch (method) {
                case RANDOM:
                    array = generateRandomArray();
                    createdArray = true;
                    break;
                case MANUAL:
                    array = manualArrayInput(scanner);
                    createdArray = true;
                    break;
                case ADD:
                    if (createdArray) {
                        array = addToArray(array, scanner);
                    } else {
                        MustCreateArray();
                    }
                    break;
                case EXIT:
                System.out.println("Exiting the program.");
                scanner.close();
                return;
                case MAX:
                if (createdArray) {
                    int max = findMax(array);
                    System.out.println("Max number is: " + max);
                } else {
                    MustCreateArray();
                }
                break;
                case MIN:
                if (createdArray) {
                    int min = findMin(array);
                    System.out.println("Max number is: " + min);
                } else {
                    MustCreateArray();
                }
                break;
                case REMOVE:
                if (createdArray) {
                    array = removeFromArray(array, scanner);
                } else {
                    MustCreateArray();
                }
                break;
                case DELETE:
                if (createdArray) {
                    array = deleteArray();
                    createdArray = false;
                } else {
                    MustCreateArray();
                }
                break;
                default:
                    System.out.println("Unknown option. Try again.");
                    continue;
            }
    
            if (createdArray) {
                System.out.println("Current array:");
                for (int i : array) {
                    System.out.print(i + " ");
                }
                System.out.println();
            }
    
            while (true) {
                System.out.println("Do you want to continue? (yes/no):");
                String answer = scanner.next().trim().toLowerCase();
            
                if (answer.equals("yes") || answer.equals("y")) {
                    break;
                } else if (answer.equals("no") || answer.equals("n")) {
                    scanner.close();
                    System.out.println("Exiting program.");
                    return;
                } else {
                    System.out.println("Invalid input. Please type 'yes' or 'no'.");
                }
            }
        }
    }


    public static int[] generateRandomArray() {
        Random rand = new Random();
        int lengthArray = rand.nextInt(50)+5;
        int[] array = new int[lengthArray];
        for (int i=0; i<lengthArray; i++){
            array[i] = rand.nextInt(201)-100;
        }
        return array;
    }

    public static int[] manualArrayInput(Scanner scanner){
        System.out.print("Enter field size: ");
        int size;
        while (true) {
            if (scanner.hasNextInt()) {
                size = scanner.nextInt();
                break;
            }else{
                InvalidInput();
                scanner.next();
            }
        }
        
        int[] array = new int[size];
        for(int i = 0; i < size; i++){
            while (true) {
                System.out.print("Enter number [" + (i + 1) + "]: ");
                if (scanner.hasNextInt()){
                    array[i] = scanner.nextInt();
                    break; 
                } else {
                    InvalidInput();
                    scanner.next();
                }
            }
        }
        return array;
    }

    public static int[] addToArray(int[] originalArray, Scanner scanner){
        System.out.print("Enter new number:");
        int newNumber;
        while (true) {
            if(scanner.hasNextInt()){
                newNumber = scanner.nextInt();
                break;
            } else {
                InvalidInput();
                scanner.next();
            }
        }
        
        int[] newArray = new int[originalArray.length+1];
        for (int i = 0; i< originalArray.length; i++){
            newArray[i] = originalArray[i];
        }

        newArray[originalArray.length] = newNumber;
        return newArray;
    }

    public static int findMax(int[] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("Array is empty");
        }
        int max = Arrays.stream(array).max().getAsInt();
    
        return max;
    }

    public static int findMin(int[] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("Array is empty");
        }
        int min = Arrays.stream(array).min().getAsInt();
    
        return min;
    }

    public static int[] removeFromArray(int[] array, Scanner scanner) {
        System.out.print("Enter number to remove: ");
        int numberToRemove;
    
        while (true) {
            if (scanner.hasNextInt()) {
                numberToRemove = scanner.nextInt();
                break;
            } else {
                InvalidInput();
                scanner.next();
            }
        }
    
        int count = 0;
        for (int value : array) {
            if (value == numberToRemove) {
                count++;
            }
        }
    
        if (count == 0) {
            System.out.println("Number not found in array.");
            return array;
        }
    
        int[] newArray = new int[array.length - count];
        int index = 0;
    
        for (int value : array) {
            if (value != numberToRemove) {
                newArray[index++] = value;
            }
        }
    
        System.out.println("Removed all occurrences of: " + numberToRemove);
        return newArray;
    }

    public static int[] deleteArray() {
        System.out.println("Array has been deleted.");
        return null;
    }


    public static ArrayMethod startMenu(Scanner scanner) {
        System.out.println("Select how to create the array:");
        System.out.println("1 - Random");
        System.out.println("2 - Manually");
        System.out.println("0 - Exit");

        int choice = -1;
        while (true) {
            System.out.println("Your choice:");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                switch (choice) {
                    case 0:
                    return ArrayMethod.EXIT;
                    case 1:
                    return ArrayMethod.RANDOM;
                    case 2:
                    return ArrayMethod.MANUAL;
                    default:
                    return ArrayMethod.UNKNOWN;
                }
            }
            else {
                InvalidInput();
                scanner.next();
            }
        }   
    }

    public static ArrayMethod editMenu(Scanner scanner) {
        System.out.println("Select how to create the array:");
        System.out.println("1 - Add a number to an existing array");
        System.out.println("2 - Finding the largest number in an existing array");
        System.out.println("3 - Finding the smallest number in an existing array");
        System.out.println("4 - Remove a number from the list");
        System.out.println("5 - Delete the list");
        System.out.println("0 - Exit");

        int choice = -1;
        while (true) {
            System.out.println("Your choice:");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                switch (choice) {
                    case 0:
                    return ArrayMethod.EXIT;
                    case 1:
                    return ArrayMethod.ADD;
                    case 2: 
                    return ArrayMethod.MAX;
                    case 3:
                    return ArrayMethod.MIN;
                    case 4:
                    return ArrayMethod.REMOVE;
                    case 5:
                    return ArrayMethod.DELETE;
                    default:
                    return ArrayMethod.UNKNOWN;
                }
            }
            else {
                InvalidInput();
                scanner.next();
            }
        }   
    }

    public static void InvalidInput(){
        System.out.println("Invalid input. Please enter a number.");
    }

    public static void MustCreateArray(){
        System.out.println("You must first create an array");
    }
}