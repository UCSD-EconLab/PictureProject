	package pictureProject;

	public class OptionBudget{

		private int option;
		private float budget;

		public OptionBudget(){

		}

		public void setValues(int option, float budget){
			this.option = option;
			this.budget = budget;
		}

		public boolean isSet(){
			return (this.option != 0 && this.budget != 0.0);
		}


		public int getOption(){
			return this.option;
		}

		public float getBudget(){
			return this.budget;
		}
	}