package net.lidongdong.bearoil.entity;

/**
*
* 
*  @author lidongdong(一个帅的惊天动地的男人)
*  @date 17/4/13
*  @explain 
*  @function 
*  @version 1.0
*   
*/

public class CarEntity {

    private  int _id;
    private String name;
    private int mSelect;
    private int model;
    private String uuid;

    public CarEntity (int _id) {
        this._id = _id;
    }

    public CarEntity() {
    }

    @Override
    public String toString() {
        return "CarEntity{" +
                "_id=" + _id +
                ", name='" + name + '\'' +
                ", mSelect=" + mSelect +
                ", model=" + model +
                ", uuid='" + uuid + '\'' +
                '}';
    }

    public int get_id () {
        return _id;
    }


    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public int getSelect() {
        return mSelect;
    }

    public void setSelect(int select) {
        mSelect = select;
    }

    public int getModel () {
        return model;
    }

    public void setModel (int model) {
        this.model = model;
    }

    public String getUuid () {
        return uuid;
    }

    public void setUuid (String uuid) {
        this.uuid = uuid;
    }
}
