import java.util.Comparator;

/**
 * @author Deanna Liu
 */
public class Treatment {

    String name;
    String diseaseTreated;
    double probabilityOfSuccess;
    double pricePerUnit;

    public Treatment(String name, String diseaseTreated, double probabilityOfSuccess, double pricePerUnit){
        this.name = name;
        this.diseaseTreated = diseaseTreated;
        this.probabilityOfSuccess = probabilityOfSuccess;
        this.pricePerUnit = pricePerUnit;
    }

    @Override
    public int hashCode() {
        int  result;
        long temp;
        result = name.hashCode();
        result = 31 * result + diseaseTreated.hashCode();
        temp = Double.doubleToLongBits(probabilityOfSuccess);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(pricePerUnit);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    public String toString() {
        return "Treatment{name='"+name+"', diseaseTreated='"+diseaseTreated+"', probabilityOfSuccess="
                +probabilityOfSuccess+", pricePerUnit="+pricePerUnit+"}";
    }

    public boolean equals(Treatment t){
        return (t.name.equals(this.name) && this.diseaseTreated.equals(t.diseaseTreated) &&
                this.probabilityOfSuccess==t.probabilityOfSuccess && this.pricePerUnit==t.pricePerUnit); //TODO
    }

    public static class PriceBasedTreatmentComparator implements Comparator<Treatment>
    {
        @Override
        public int compare(Treatment t1, Treatment t2)
        {
            if(t1 == null || t2 == null)
                throw new NullPointerException();
            int outcome = 0;
            if(t1.pricePerUnit<t2.pricePerUnit)
                outcome =  -1;
            else if(t1.pricePerUnit == t2.pricePerUnit)
                outcome =  0;
            else if(t1.pricePerUnit>t2.pricePerUnit)
                outcome = 1;
            return outcome;
        }

        public String toString() {
            return "price";
        }
    }

    public static class SuccessBasedTreatmentComparator implements Comparator<Treatment>{
        @Override
        public int compare(Treatment t1, Treatment t2)
        {
            if(t1 == null || t2 == null)
                throw new NullPointerException();
            int outcome = 0;
            if(t1.probabilityOfSuccess<t2.probabilityOfSuccess)
                outcome =  -1;
            else if(t1.probabilityOfSuccess == t2.probabilityOfSuccess)
                outcome =  0;
            else if(t1.probabilityOfSuccess>t2.probabilityOfSuccess)
                outcome = 1;
            return outcome;
        }

        public String toString() {
            return "success";
        }
    }
}