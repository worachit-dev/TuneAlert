import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Song — ADT แทน "เพลง" หนึ่งเพลง
 *
 * ⚠️ โค้ดตั้งต้นนี้ "ใช้งานได้" แต่มีบั๊กแบบเดียวกับกรณีศึกษาในสไลด์:
 *    rep exposure ทั้งขาเข้าและขาออก, producer ที่แอบ mutate ตัวเอง,
 *    ไม่ validate input และยังไม่ override equals/hashCode
 *
 * ภารกิจของคุณ: ทำให้ Song เป็น immutable class ที่ถูกต้อง "ครบสูตร 6 ข้อ"
 * และ override equals()/hashCode() ตามสัญญาของ Java (ดูรายละเอียดใน README.md)
 */
public final class Song {

    private final String title;
    private final String artist;
    private final List<String> tags;

    /**
     * สร้างเพลง 1 เพลง
     * @param title ชื่อเพลง
     * @param artist ชื่อผู้แต่งเพลง
     * @param tags  หมวดหมู่เพลง
     * @throws illegelArgumentException เมื่อ 
     */
    public Song(String title, String artist, List<String> tags) {
        // TODO(1.1): validate input — title/artist ห้าม null/ว่าง,
        //            tags ห้าม null และห้ามมีสมาชิกเป็น null/ว่าง
        //            ผิดเงื่อนไขให้ throw IllegalArgumentException
        if (title==null||title==""||artist==null||artist=="") 
            throw new IllegalArgumentException(); 
        if(tags==null||tags.contains(null)||tags.contains(""))
            throw new IllegalArgumentException();
        this.title = title;
        this.artist = artist;
        this.tags= new ArrayList<>(tags);
        checkRep();
    }

    private void checkRep(){
        assert title!=null && title!="";
        assert artist!=null && artist!="";
        assert tags!=null && !tags.contains(null) && !tags.contains("");
    
    }

    // ---------- observers ----------

    public String title() {
        return title;
    }

    public String artist() {
        return artist;
    }

    public List<String> tags() {
        // TODO(1.3): ✗ ส่งลูกศรออกไปตรง ๆ = rep exposure ขาออก → คืน "สำเนา"
        return new ArrayList<>(tags);
    }

    // ---------- producer ----------

    /**
     * spec: คืน Song "ตัวใหม่" ที่มีแท็กเพิ่มต่อท้าย — ห้ามแก้ตัวเดิม
     * @throws IllegalArgumentException เมื่อ tag เป็น null/ว่าง
     */
    public Song withTag(String tag) {
        // TODO(1.4): ✗ โค้ดนี้ mutate ตัวเอง! ต้องสร้างและคืน Song ตัวใหม่แทน
        //            (อย่าลืม validate tag ด้วย)
        if (tag==null||tag=="") throw new IllegalArgumentException();
        List<String> next = new ArrayList<>(tags);
        next.add(tag);
        return new Song(title, artist,next);
    }

    // ---------- equality ----------

    // TODO(1.5): override equals(Object o) แบบ structural equality
    //            เทียบ title, artist และ tags ทีละ field
    //            ตามลำดับมาตรฐาน: ตัวเอง → ชนิด (instanceof) → cast → เทียบ field
    //            ระวัง: ต้องรับ Object ไม่ใช่ Song ไม่งั้นเป็น overload ไม่ใช่ override!
    @Override
    public boolean equals(Object o){
        if (this == o) return true; 
        if (!(o instanceof Song)) 
        return false; 
        Song s = (Song) o; 
        return title.equals(s.title) && artist.equals(s.artist) && tags.equals(s.tags);

    }
     

    // TODO(1.6): override hashCode() ให้สอดคล้องกับ equals
    //            (คำนวณจาก field ชุดเดียวกัน — Objects.hash(...) ช่วยได้)
    
    @Override
    public int hashCode(){
        return Objects.hash(title,artist,tags);
    }

    @Override
    public String toString() {
        return title + " — " + artist + " " + tags;
    }
}
