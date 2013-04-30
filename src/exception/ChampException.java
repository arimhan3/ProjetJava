package exception;

public class ChampException extends Exception {
		private String champ;
		public ChampException(String cha)
		{
			champ=cha;
		}
		public String toString()
		{
			return champ;
		}
}
