public class PalindromoAir {
    private Ticket[] asientos;

    public PalindromoAir() {
        this.asientos = new Ticket[30];
    }
    
    public int firstAvailable() {
        return firstAvailable(0);
    }

    private int firstAvailable(int disponibilidad) {
        if (disponibilidad >= asientos.length) {
            return -1;
        }
        if (asientos[disponibilidad] == null) {
            return disponibilidad;
        }
        return firstAvailable(disponibilidad + 1);
    }
    
    public Ticket[] getAsientos() {
        return asientos;
    }
    
    public int searchPassenger(String name) {
        return searchPassenger(0, name);
    }
    
    private int searchPassenger(int disponibilidad, String name) {
        if (disponibilidad >= asientos.length) {
            return -1;
        }
        if (asientos[disponibilidad] != null && asientos[disponibilidad].getNombrePasajero().equals(name)) {
            return disponibilidad;
        }
        return searchPassenger(disponibilidad + 1, name);
    }

    public boolean isPalindromo(String name) {
        return isPalindromo(name, 0, name.length()-1);
    }

    private boolean isPalindromo(String name, int inicio,int fin) {
        if (inicio >= fin) {
            return true;
        }
        if (name.charAt(inicio) != name.charAt(fin)){
            return false;
        }
        return isPalindromo(name,inicio+1,fin-1);
    }
    
    public void printPassengers(){
        printPassengers(0);
    }

    private void printPassengers(int disponibilidad){
        if (disponibilidad>=asientos.length) {
            return;
        }
        if (asientos[disponibilidad]!=null) {
            System.out.println(asientos[disponibilidad].print());
        }
        printPassengers(disponibilidad + 1);
    }

    public double income(){
        return income(0);
    }

    private double income(int disponibilidad){
        if (disponibilidad>=asientos.length){
            return 0;
        }
        if (asientos[disponibilidad]!=null){
            return asientos[disponibilidad].getTotalTicket() + income(disponibilidad + 1);
        }
        return income(disponibilidad + 1);
    }

    public void reset() {
        reset(0);
    }

    private void reset(int disponibilidad){
        if (disponibilidad>=asientos.length){
            return;
        }
        asientos[disponibilidad]=null;
        reset(disponibilidad + 1);
    }
    
    public String sellTicket(String name) {
        int disponibilidad=firstAvailable();
        if (disponibilidad!=-1) {
            double precioTicket=800;
            if(isPalindromo(name)){
                precioTicket*=0.8;
            }
            asientos[disponibilidad]=new Ticket(name, precioTicket);
            return "Monto a pagar: $."+precioTicket;
        } else {
            return "No hay asientos disponibles";
        }
    }
    
    public boolean cancelTicket(String name){
        int pasajero = searchPassenger(name);
        if (pasajero!=-1){
            asientos[pasajero] = null;
            return true;
        }
        return false;
    }

    public void dispatch() {
        reset();
    }
}
