package it.zygotecode.amnyscraft.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

@SuppressWarnings("deprecation")
public class AntiSwear implements Listener
{
	@EventHandler(priority = EventPriority.MONITOR)
	public void onChatMessage(PlayerChatEvent event)
	{
		String msg = event.getMessage();
		msg = msg.toLowerCase();
		boolean need1 = false;
		String[] blockedWords = {"pezzo di merda", "merda", "cazzo", "minchia", "dio", "porco", "madonna", "figlio di puttana", "puttana", "zoccola", "troia", "coglione", "rompicoglioni", "mongoloide", "bastardo", "stronzo", "vaffanculo", "vai a cagare", "deficiente", "imbecille", "frocio", "culone", "mignotta", "baldracca", "leccaculo", "lecchino", "figlio di mignotta", "mignotta", "negro", "pisello", "pisellino", "figa", "vagina", "tette", "culo", "porco", "sega", "pompino", "servizio orale", "pippa", "sfiga", "sfigato", "figlio della merda", "figlio di merda", "lezzo", "nabbo", "scarso", "scarsone", "minchione", "stronzata", "fuck", "suck", "dick", "boobs", "boob", "tetta", "slut", "whore", "bitch", "pussy", "asshole", "ass licks", "ass hole", "asslicks", "penis", "cock", "black cock", "big cock", "jerk off", "jerk", "nake", "naked", "pedo", "oral service", "nude", "shit", "crap", "gesu", "gesù", "pork", "pig", "trap", "sfera ebbasta", "ghali", "drefgold", "dark polo gang", "noob", "gemitaiz", "madman", "noob", "poor", "badass", "cazzuto", "cagna", "bloody hell", "bullshit", "puttanate", "stronzate", "puttanata", "cocksucker", "cow", "creep", "cunt", "dickhead", "dirty fucker", "dirty old bastard", "donkey", "dud", "dullard", "dumbass", "fallito", "stupido", "vecchio porco basatardo", "porco", "bastardo", "testa di cazzo", "testa di minchia", "faccia di minchia", "faccia di cazzo", "faccia da pisello", "testa da pisello", "fat head", "fuck", "dummy", "to fuck", "vai a farti fottere", "fucking hell", "porca puttana", "get lost", "get stuffed", "suicide yourself", "suicidati", "ti ammazzo", "ammazzati", "go fuck yourself", "vaffanculo", "go to hell", "vai al diavolo", "i don't give a flying fuck", "non me ne frega un cazzo", "me ne frego", "i don't give a shit", "idiot", "idiota", "i'll kick his ass", "gli rompo il culo", "jackass", "mind your own fucking business", "fatti i cazzi tuoi", "mother fucker", "muthafucka", "shut the fuck up", "shut fuck up", "shut up", "stai zitto", "stai cazzo zitto", "stai zitto cazzo", "cazzo stai zitto", "piss", "pisciare", "piss off", "platface", "cornuto", "pussy", "to screw", "shit", "shitface", "shit face", "silly twit", "puttanella", "gay", "son of a bitch", "tits", "tua mamma", "ur mom", "ur mother", "your mom", "your mother", "up yourselfwhore", "segaiolo", "wanker", "yokel", "bifolco", "puttana la madonna", "porco dio", "porca madonna", "gesù porco", "porco gesù"};
		
		for (String bs: blockedWords)
		{
			bs = bs.toLowerCase();
			String asterisks = "";
			
			for (int i = 0; i < bs.length(); i++)
			{
				asterisks += "*";
			}
			
			if (msg.contains(bs))
			{
				msg = msg.replace(bs, asterisks);
				need1 = true;
			}
		}
		
		String lol = filterString(msg);
		boolean need = false;
		
		for (String bs: blockedWords)
		{
			bs = bs.toLowerCase();
			bs = bs.replace(" ", "");
			bs = filterString(bs);
			String asterisks = "";
			
			for (int i = 0; i < bs.length(); i++)
			{
				asterisks += "*";
			}
			
			if (lol.contains(bs))
			{
				lol = lol.replace(bs, asterisks);
				need = true;
			}
		}
		
		if (need)
		{
			event.setMessage(lol);
		}
		else
		{
			if (need1)
			{
				event.setMessage(msg);
			}
		}
	}
	
	public String filterString(String s)
	{
		s = s.toLowerCase();
		String filteredString = "";
		
		String[] allowedChars = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
		
		for (char c: s.toCharArray())
		{
			String sh = Character.toString(c);
			
			for (String ac: allowedChars)
			{
				if (sh == ac || sh == ac.toUpperCase())
				{
					filteredString += sh;
				}
			}
		}
		
		return filteredString;
	}
}