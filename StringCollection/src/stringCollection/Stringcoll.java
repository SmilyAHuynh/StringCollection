package stringCollection;

import java.util.*;

public class Stringcoll {
	private int how_many;
	private btNode c;
	
	private static class btNode {
		String info;
		btNode lt;
		btNode rt;

		public btNode() {
			lt = null;
			rt = null;
			info = "";
		}
		
		private btNode(String i, btNode left, btNode right) {
			info = i;
			lt = left;
			rt = right;
		}
	}

	// Initializes Stringcoll instance
	public Stringcoll() {
		c = null;
		how_many = 0;
	}

	// Initializes Stringcoll instance which can hold i ints
	public Stringcoll(int i) {
		c = null;
		how_many = 0;
	}

	// Copies contents of Stringcoll instance named obj to this
	public void copy(Stringcoll obj) {
		if (this != obj) {
			c = btclone(obj.c);
			how_many = obj.how_many;
		}
	}

	// Returns true if collection contains int i, false if otherwise
	public boolean belongs(String i) {
		btNode p = c;
		while (p != null && !p.info.equals(i)) {
			if (p.info.compareTo(i) < 0)
				p = p.rt;
			else
				p = p.lt;
		}
		return p != null;
	}

	// Inserts i into collection. If collection already contains i, function
	// returns
	// if collection is filled to capacity, capacity is doubled
	public void insert(String i) {
		if (c == null) {
			c = new btNode(i, null, null);
			how_many++;
			return;
		}

		btNode p = c;
		btNode prev = p;
		while (p != null && !p.info.equals(i)) {
			prev = p;
			if (p.info.compareTo(i) < 0)
				p = p.rt;
			else
				p = p.lt;
		}

		if (p == null) {
			if (prev.info.compareTo(i) < 0)
				prev.rt = new btNode(i, null, null);
			else
				prev.lt = new btNode(i, null, null);
			how_many++;
		}
	}

	// If collection contains i, i will be omitted
	public void omit(String i) {
		btNode p = c;
		btNode prev = null;
		while (p != null && !p.info.equals(i)) {
			prev = p;
			if (p.info.compareTo(i) < 0)
				p = p.rt;
			else
				p = p.lt;
		}

		if (p != null) {
			btNode q = p;
			if (p.rt == null)
				q = p.lt;
			else if (p.lt == null)
				q = p.rt;
			else {
				btNode j = p.lt;
				if (j.rt == null) {
					q = j;
					q.rt = p.rt;
				} else {
					while (j.rt.rt != null)
						j = j.rt;
					q = j.rt;
					j.rt = q.lt;
					q.rt = p.rt;
					q.lt = p.lt;
				}
			}

			if (prev == null)
				c = q;
			else if (prev.rt == p)
				prev.rt = q;
			else
				prev.lt = q;

			how_many--;
		}
	}

	// Returns amount of ints stored in collection
	public int get_howmany() {
		return how_many;
	}
	
	private static void btprint(btNode b) {
		if (b == null)
			return;
		btprint(b.lt);
		System.out.println(b.info);
		btprint(b.rt);
	}

	// Prints contents of collection to output
	public void print() {
		btprint(c);
	}

	// returns true if both Stringcoll instances contain
	// identical int collections
	public boolean equals(Stringcoll obj) {
		if (how_many != obj.how_many)
			return false;
		String a1[] = new String[how_many];
		String a2[] = new String[how_many];
		int i = 0;
		btToArray(c, a1, i);
		i = 0;
		btToArray(obj.c, a2, i);

		boolean ret = true;
		for (i = 0; ret && i < how_many; i++)
			ret = (a1[i].equals(a2[i]));

		return ret;
	}

	private static btNode btclone(btNode b) {
		if (b == null)
			return null;
		return new btNode(b.info, btclone(b.lt), btclone(b.rt));
	}

	private static int btToArray(btNode b, String[] a, int i) {
		if (b != null) {
			i = btToArray(b.lt, a, i);
			a[i++] = b.info;
			i = btToArray(b.rt, a, i);
		}
		return i;
	}
}
