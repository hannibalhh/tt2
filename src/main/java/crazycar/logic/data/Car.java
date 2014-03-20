package crazycar.logic.data;

public class Car {
	
	public boolean isEmpty(){
		return false;
	}
		
	@Override
	public String toString() {
		return "Car";
	}

	public class EmptyCar extends Car{
		public boolean isEmpty(){
			return true;
		}
		
		@Override
		public String toString() {
			return "EmptyCar";
		}
	}
}
