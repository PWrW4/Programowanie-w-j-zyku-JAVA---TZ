package lab14;

import java.math.BigDecimal;

public class Cubic {

    private static final BigDecimal TWO_PI =  new BigDecimal(2.0 * Math.PI);
    private static final BigDecimal FOUR_PI = new BigDecimal(4.0 * Math.PI);

    public int nRoots;


    public BigDecimal x1;


    public BigDecimal x2;


    public BigDecimal x3;


    public Cubic()
    {
    }


    public void solve
    (BigDecimal a,
     BigDecimal b,
     BigDecimal c,
     BigDecimal d)
    {

        if (a.equals(new BigDecimal(0)))
        {
            throw new RuntimeException ("Cubic.solve(): a = 0");
        }

        BigDecimal denom = a;
        a = b.divide(denom,BigDecimal.ROUND_HALF_EVEN);
        b = c.divide(denom,BigDecimal.ROUND_HALF_EVEN);
        c = d.divide(denom,BigDecimal.ROUND_HALF_EVEN);

        BigDecimal a_over_3 = a.divide(new BigDecimal(3.0),BigDecimal.ROUND_HALF_EVEN);
        BigDecimal Q = b.multiply(new BigDecimal(3)).subtract(a.pow(2)).divide(new BigDecimal(9),BigDecimal.ROUND_HALF_EVEN);
        BigDecimal Q_CUBE = Q.pow(3);
        BigDecimal R = new BigDecimal(9).multiply(a).multiply(b).subtract(c.multiply(new BigDecimal(27))).subtract(a.pow(3).multiply(new BigDecimal(2))).divide(new BigDecimal(54.0),BigDecimal.ROUND_HALF_EVEN);
        BigDecimal R_SQR = R.pow(2);
        BigDecimal D = Q_CUBE.add(R_SQR);

        if (D.doubleValue() < 0.0)
        {
            nRoots = 3;
            BigDecimal theta = new BigDecimal(Math.acos (R.divide(new BigDecimal(Math.sqrt (-Q_CUBE.doubleValue())),BigDecimal.ROUND_HALF_EVEN).doubleValue()));
            BigDecimal SQRT_Q = new BigDecimal(Math.sqrt (Q.negate().doubleValue()));
            x1 = new BigDecimal(Math.cos(theta.divide(new BigDecimal(3),BigDecimal.ROUND_HALF_EVEN).doubleValue())).multiply(SQRT_Q).multiply(new BigDecimal(2)).subtract(a_over_3);
            x2 = new BigDecimal(Math.cos ((theta.add(TWO_PI)).divide(new BigDecimal(3.0),BigDecimal.ROUND_HALF_EVEN).doubleValue())).multiply(SQRT_Q).multiply(new BigDecimal(2)).subtract(a_over_3);
            x3 = new BigDecimal(Math.cos ((theta.add(FOUR_PI)).divide(new BigDecimal(3.0),BigDecimal.ROUND_HALF_EVEN).doubleValue())).multiply(SQRT_Q).multiply(new BigDecimal(2)).subtract(a_over_3);
            sortRoots();
        }
        else if (D.doubleValue() > 0.0)
        {
            nRoots = 1;
            BigDecimal SQRT_D = new BigDecimal(Math.sqrt (D.doubleValue()));
            BigDecimal S = new BigDecimal(Math.cbrt (R.add( SQRT_D).doubleValue()));
            BigDecimal T = new BigDecimal(Math.cbrt (R.subtract(SQRT_D).doubleValue()));
            x1 = (S.add( T)).subtract(a_over_3);
            x2 = null;
            x3 = null;
        }
        else
        {
            nRoots = 3;
            BigDecimal CBRT_R = new BigDecimal(Math.cbrt (R.doubleValue()));
            x1 = CBRT_R.multiply(new BigDecimal(2)).subtract(a_over_3);
            x2 = x3 = CBRT_R.subtract(a_over_3);
            sortRoots();
        }
    }


    private void sortRoots()
    {
        if (x1.doubleValue() < x2.doubleValue())
        {
            BigDecimal tmp = x1; x1 = x2; x2 = tmp;
        }
        if (x2.doubleValue() < x3.doubleValue())
        {
            BigDecimal tmp = x2; x2 = x3; x3 = tmp;
        }
        if (x1.doubleValue() < x2.doubleValue())
        {
            BigDecimal tmp = x1; x1 = x2; x2 = tmp;
        }
    }


    public static void main(String[] args) throws Exception {

        BigDecimal a = new BigDecimal(1).setScale(12, BigDecimal.ROUND_HALF_UP);
        BigDecimal b = new BigDecimal(4).setScale(12, BigDecimal.ROUND_HALF_UP);;
        BigDecimal c = new BigDecimal(7).setScale(12, BigDecimal.ROUND_HALF_UP);;
        BigDecimal d = new BigDecimal(8).setScale(12, BigDecimal.ROUND_HALF_UP);;
        Cubic cubic = new Cubic();
        cubic.solve(a, b, c, d);
        System.out.println("x1 = " + cubic.x1);
        if (cubic.nRoots == 3) {
            System.out.println("x2 = " + cubic.x2);
            System.out.println("x3 = " + cubic.x3);
        }
    }

}
