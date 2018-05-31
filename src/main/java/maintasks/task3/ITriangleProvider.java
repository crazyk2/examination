package maintasks.task3;


public class ITriangleProvider {

    public ITriangle getTriangle() {
        return new ITriangle(){
            @Override
            public int getX1() {
                return 1;
            }

            @Override
            public int getY1() {
                return 1;
            }

            @Override
            public int getX2() {
                return 1;
            }

            @Override
            public int getY2() {
                return 3;
            }

            @Override
            public int getX3() {
                return 3;
            }

            @Override
            public int getY3() {
                return 1;
            }
        };
    }


}
