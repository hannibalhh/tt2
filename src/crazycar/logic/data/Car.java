package crazycar.logic.data;

public class Car {
	
	public final Car ferrari = new Car();
	public final EmptyCar empty = new EmptyCar();
	
	private Car(){}
	
	public boolean isEmpty(){
		return false;
	}
		
	@Override
	public String toString() {
		return "Car";
	}

	private class EmptyCar extends Car{
		public boolean isEmpty(){
			return true;
		}
		
		@Override
		public String toString() {
			return "EmptyCar";
		}
	}
}
