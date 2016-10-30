package multiStringCollection;

public class MultiStringColl {

    private static class btNode {

     public btNode lt;
        public btNode rt;
        public String info;
        public int how_many;

        public btNode() {
            lt = null;
            rt = null;
            info = "";
            how_many = 1;
        }

        public btNode(String i, btNode left, btNode right) {
            lt = left;
            rt = right;
            info = i;
            how_many = 1;
        }

        public btNode(String i, btNode left, btNode right, int hm) {
            lt = left;
            rt = right;
            info = i;
            how_many = hm;
        }
    }
    private btNode c;
    private int how_many;

    // Initializes MultiStringcoll instance which can hold 500 ints
    public MultiStringColl() {
        c = null;
        how_many = 0;
    }

    // Initializes MultiStringcoll instance which can hold i ints
    public MultiStringColl(int i) {
        c = null;
        how_many = 0;
    }

    // Copys contents of MultiStringcoll instance named obj to this
    public void copy(MultiStringColl obj) {
        if (this != obj) {
            c = btclone(obj.c);
            how_many = obj.how_many;
        }
    }

    // Returns true if collection contains int i, false if otherwise
    public boolean belongs(String i) {
        btNode p = c;
        while (p != null && !p.info.equals(i)) {
            if (p.info.compareTo(i) < 0) {
                p = p.rt;
            } else {
                p = p.lt;
            }
        }
        return p != null;
    }

    // Inserts i into collection. If collection already contains i, function returns
    //   if collection is filled to capacity, capacity is doubled
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
            if (p.info.compareTo(i) < 0) {
                p = p.rt;
            } else {
                p = p.lt;
            }
        }

        if (p == null) {
            if (prev.info.compareTo(i) < 0) {
                prev.rt = new btNode(i, null, null);
            } else {
                prev.lt = new btNode(i, null, null);
            }
            how_many++;
        } else {
            p.how_many++;
        }
    }

    // If collection contains i, i will be omitted
    public void omit(String i) {
        btNode p = c;
        btNode prev = null;
        while (p != null && !p.info.equals(i)) {
            prev = p;
            if (p.info.compareTo(i) < 0) {
                p = p.rt;
            } else {
                p = p.lt;
            }
        }

        if (p != null) {
            p.how_many--;
        }
        if (p != null && p.how_many == 0) {
            btNode q = p;
            if (p.rt == null) {
                q = p.lt;
            } else if (p.lt == null) {
                q = p.rt;
            } else {
                btNode j = p.lt;
                if (j.rt == null) {
                    q = j;
                    q.rt = p.rt;
                } else {
                    while (j.rt.rt != null) {
                        j = j.rt;
                    }
                    q = j.rt;
                    j.rt = q.lt;
                    q.rt = p.rt;
                    q.lt = p.lt;
                }
            }

            if (prev == null) {
                c = q;
            } else if (prev.rt == p) {
                prev.rt = q;
            } else {
                prev.lt = q;
            }

            how_many--;
        }
    }

    // Returns amount of ints stored in collection
    public int get_howmany() {
        return how_many;
    }

    // Prints contents of collection to output
    public void print() {
        btprint(c);
    }

    // returns true if both MultiStringcoll instances contain
    //   identical int collections
    public boolean equals(MultiStringColl obj) {
        if (how_many != obj.how_many) {
            return false;
        }
        String a1[] = new String[how_many];
        String a2[] = new String[how_many];
        int i = 0;
        btToArray(c, a1, i);
        i = 0;
        btToArray(obj.c, a2, i);

        boolean ret = true;
        for (i = 0; ret && i < how_many; i++) {
            ret = (a1[i].equals(a2[i]));
        }

        return ret;
    }

    private static btNode btclone(btNode b) {
        if (b == null) {
            return null;
        }

        return new btNode(b.info, btclone(b.lt), btclone(b.rt), b.how_many);
    }

    private static int btToArray(btNode b, String[] a, int i) {
        if (b != null) {
            i = btToArray(b.lt, a, i);
            a[i++] = b.info + Integer.toString(b.how_many); // i++ returns i before it adds 1, so a[i] == a[i++] != a[++i]
            i = btToArray(b.rt, a, i);
        }
        return i;
    }

    private static void btprint(btNode b) {
        if (b == null) {
            return;
        }
//        System.out.printf("%d; lt: %d, rt: %d\n", b.info, (b.l != null) ? b.l.info : -1, (b.r != null) ? b.r.info : -1);
        btprint(b.lt);
        System.out.println(Integer.toString(b.how_many) + " " + b.info);
        btprint(b.rt);
    }
}
