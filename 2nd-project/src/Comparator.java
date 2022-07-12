public class Comparator {

    enum choosePriority {
        MIN,
        MAX
    }

    public choosePriority priority = choosePriority.MIN;

    public boolean compareTo(City c1, City c2) {

       if (priority == choosePriority.MIN) {

           if (c1.getDensity() > c2.getDensity()) {
               return false;
           } else if (c1.getDensity() == c2.getDensity()) {
               if (c1.getName().compareTo(c2.getName()) > 0) {
                   return false;
               } else if (c1.getName().equals(c2.getName())) {
                   if (c1.getID() < c2.getID()) {
                       return false;
                   }
               }
           }
           return true;
       } else {
           if (c1.getDensity() > c2.getDensity()) {
               return true;
           } else if (c1.getDensity() == c2.getDensity()) {
               if (c1.getName().compareTo(c2.getName()) > 0) {
                   return true;
               } else if (c1.getName().equals(c2.getName())) {
                   if (c1.getID() < c2.getID()) {
                       return true;
                   }
               }
           }
           return false;

       }
    }
}

