
public enum CommandsIdentifire
{

		PRESS_MOUSE(-1),
		RELEASE_MOUSE(-2),
		PRESS_KEY(-3),
		RELEASE_KEY(-4),
		MOVE_MOUSE(-5);

		private int habijabi;

		CommandsIdentifire(int habijabi){
			this.habijabi = habijabi;
		}

		public int getEvent(){
			return habijabi;
		}

}
