public class Point3d {
    private double xCoord;
    private double yCoord;
    private double zCoord;
    public Point3d(double x, double y, double z){
        setX(x);
        setY(y);
        setZ(z);
    }
    public Point3d(){ this(0.0,0.0,0.0); }
    public double getX(){ return xCoord; }
    public double getY(){ return yCoord; }
    public double getZ(){ return zCoord; }
    public void setX(double val){ xCoord = val; }
    public void setY(double val){ yCoord = val; }
    public void setZ(double val){ zCoord = val; }
    public boolean equal2point(Point3d point){
        if(this.getX() == point.getX() &&
           this.getY() == point.getY() &&
           this.getZ() == point.getZ()) return true;
        return false;
    }
    public double distanceTo(Point3d point){
        return Math.abs(Math.sqrt(
                  (Math.pow(getX(),2)+Math.pow(point.getX(),2))
                + (Math.pow(getY(),2)+Math.pow(point.getY(),2))
                + (Math.pow(getZ(),2)+Math.pow(point.getZ(),2))));
    }

}
