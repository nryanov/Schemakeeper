/**
 * Autogenerated by Thrift
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 */
package schemakeeper.generated.thrift;


import org.apache.thrift.*;
import org.apache.thrift.meta_data.FieldMetaData;
import org.apache.thrift.meta_data.FieldValueMetaData;
import org.apache.thrift.protocol.*;

import java.util.*;

// No additional import required for struct/union.

public class ThriftMsgV5 implements TBase<ThriftMsgV5, ThriftMsgV5._Fields>, java.io.Serializable, Cloneable {
  private static final TStruct STRUCT_DESC = new TStruct("ThriftMsgV5");

  private static final TField F1_FIELD_DESC = new TField("f1", TType.STRING, (short)1);
  private static final TField F2_FIELD_DESC = new TField("f2", TType.STRING, (short)2);
  private static final TField F3_FIELD_DESC = new TField("f3", TType.STRING, (short)3);
  private static final TField F4_FIELD_DESC = new TField("f4", TType.STRING, (short)4);


  public String f1;
  public String f2;
  public String f3;
  public String f4;

  /** The set of fields this object contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements TFieldIdEnum {
    F1((short)1, "f1"),
    F2((short)2, "f2"),
    F3((short)3, "f3"),
    F4((short)4, "f4");
  
    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();
  
    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }
  
    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // F1
          return F1;
        case 2: // F2
          return F2;
        case 3: // F3
          return F3;
        case 4: // F4
          return F4;
        default:
          return null;
      }
    }
  
    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }
  
    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }
  
    private final short _thriftId;
    private final String _fieldName;
  
    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }
  
    public short getThriftFieldId() {
      return _thriftId;
    }
  
    public String getFieldName() {
      return _fieldName;
    }
  }


  // isset id assignments

  public static final Map<_Fields, FieldMetaData> metaDataMap;
  
  /**
   * FieldValueMetaData.type returns TType.STRING for both string and binary field values.
   * This set can be used to determine if a FieldValueMetaData with type TType.STRING is actually
   * declared as binary in the idl file.
   */
  public static final Set<FieldValueMetaData> binaryFieldValueMetaDatas;
  
  private static FieldValueMetaData registerBinaryFieldValueMetaData(FieldValueMetaData f, Set<FieldValueMetaData> binaryFieldValues) {
    binaryFieldValues.add(f);
    return f;
  }
  
  static {
    Map<_Fields, FieldMetaData> tmpMap = new EnumMap<_Fields, FieldMetaData>(_Fields.class);
    Set<FieldValueMetaData> tmpSet = new HashSet<FieldValueMetaData>();
    tmpMap.put(_Fields.F1, new FieldMetaData("f1", TFieldRequirementType.REQUIRED,
      new FieldValueMetaData(TType.STRING)));
    tmpMap.put(_Fields.F2, new FieldMetaData("f2", TFieldRequirementType.OPTIONAL,
      new FieldValueMetaData(TType.STRING)));
    tmpMap.put(_Fields.F3, new FieldMetaData("f3", TFieldRequirementType.OPTIONAL,
      new FieldValueMetaData(TType.STRING)));
    tmpMap.put(_Fields.F4, new FieldMetaData("f4", TFieldRequirementType.OPTIONAL,
      new FieldValueMetaData(TType.STRING)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    binaryFieldValueMetaDatas = Collections.unmodifiableSet(tmpSet);
    FieldMetaData.addStructMetaDataMap(ThriftMsgV5.class, metaDataMap);
  }

  /**
   * Returns a map of the annotations and their values for this struct declaration.
   * See fieldAnnotations or valueAnnotations for the annotations attached to struct fields
   * or enum values.
   */
  public static final Map<String, String> structAnnotations;
  static {
    structAnnotations = Collections.emptyMap();
  }

  /**
   * Returns a map of the annotations for each of this struct's fields, keyed by the field.
   * See structAnnotations for the annotations attached to this struct's declaration.
   */
  public static final Map<_Fields, Map<String, String>> fieldAnnotations;
  static {
    fieldAnnotations = Collections.emptyMap();
  }

  /**
   * Returns the set of fields that have a configured default value.
   * The default values for these fields can be obtained by
   * instantiating this class with the default constructor.
   */
  public static final Set<_Fields> hasDefaultValue;
  static {
    Set<_Fields> tmp = EnumSet.noneOf(_Fields.class);
    tmp.add(_Fields.F2);
    hasDefaultValue = Collections.unmodifiableSet(tmp);
  }


  public ThriftMsgV5() {
    this.f2 = "test";
  }

  public ThriftMsgV5(
    String f1)
  {
    this();
    this.f1 = f1;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public ThriftMsgV5(ThriftMsgV5 other) {
    if (other.isSetF1()) {
      this.f1 = other.f1;
    }
    if (other.isSetF2()) {
      this.f2 = other.f2;
    }
    if (other.isSetF3()) {
      this.f3 = other.f3;
    }
    if (other.isSetF4()) {
      this.f4 = other.f4;
    }
  }

  public static List<String> validateNewInstance(ThriftMsgV5 item) {
    final List<String> buf = new ArrayList<String>();

    if (!item.isSetF1()) {
       buf.add("Required field 'f1' in type 'ThriftMsgV5' was not present.");
    }

    return buf;
  }

  public ThriftMsgV5 deepCopy() {
    return new ThriftMsgV5(this);
  }

  @Override
  public void clear() {
    this.f1 = null;
    this.f2 = "test";
    this.f3 = null;
    this.f4 = null;
  }

  public String getF1() {
    return this.f1;
  }

  public ThriftMsgV5 setF1(String f1) {
    this.f1 = f1;
    
    return this;
  }

  public void unsetF1() {
    this.f1 = null;
  }

  /** Returns true if field f1 is set (has been assigned a value) and false otherwise */
  public boolean isSetF1() {
    return this.f1 != null;
  }

  public void setF1IsSet(boolean value) {
    if (!value) {
      this.f1 = null;
    }
  }

  public String getF2() {
    return this.f2;
  }

  public ThriftMsgV5 setF2(String f2) {
    this.f2 = f2;
    
    return this;
  }

  public void unsetF2() {
    this.f2 = null;
  }

  /** Returns true if field f2 is set (has been assigned a value) and false otherwise */
  public boolean isSetF2() {
    return this.f2 != null;
  }

  public void setF2IsSet(boolean value) {
    if (!value) {
      this.f2 = null;
    }
  }

  public String getF3() {
    return this.f3;
  }

  public ThriftMsgV5 setF3(String f3) {
    this.f3 = f3;
    
    return this;
  }

  public void unsetF3() {
    this.f3 = null;
  }

  /** Returns true if field f3 is set (has been assigned a value) and false otherwise */
  public boolean isSetF3() {
    return this.f3 != null;
  }

  public void setF3IsSet(boolean value) {
    if (!value) {
      this.f3 = null;
    }
  }

  public String getF4() {
    return this.f4;
  }

  public ThriftMsgV5 setF4(String f4) {
    this.f4 = f4;
    
    return this;
  }

  public void unsetF4() {
    this.f4 = null;
  }

  /** Returns true if field f4 is set (has been assigned a value) and false otherwise */
  public boolean isSetF4() {
    return this.f4 != null;
  }

  public void setF4IsSet(boolean value) {
    if (!value) {
      this.f4 = null;
    }
  }

  @SuppressWarnings("unchecked")
  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case F1:
      if (value == null) {
        unsetF1();
      } else {
        setF1((String)value);
      }
      break;
    case F2:
      if (value == null) {
        unsetF2();
      } else {
        setF2((String)value);
      }
      break;
    case F3:
      if (value == null) {
        unsetF3();
      } else {
        setF3((String)value);
      }
      break;
    case F4:
      if (value == null) {
        unsetF4();
      } else {
        setF4((String)value);
      }
      break;
    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case F1:
      return getF1();
    case F2:
      return getF2();
    case F3:
      return getF3();
    case F4:
      return getF4();
    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case F1:
      return isSetF1();
    case F2:
      return isSetF2();
    case F3:
      return isSetF3();
    case F4:
      return isSetF4();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof ThriftMsgV5)
      return this.equals((ThriftMsgV5)that);
    return false;
  }

  public boolean equals(ThriftMsgV5 that) {
    return equalsWithoutPassthrough(that);
  }

  private boolean equalsWithoutPassthrough(ThriftMsgV5 that) {
    if (that == null)
      return false;
    boolean this_present_f1 = true && this.isSetF1();
    boolean that_present_f1 = true && that.isSetF1();
    if (this_present_f1 || that_present_f1) {
      if (!(this_present_f1 && that_present_f1))
        return false;
      if (!this.f1.equals(that.f1))
        return false;
    }
    boolean this_present_f2 = true && this.isSetF2();
    boolean that_present_f2 = true && that.isSetF2();
    if (this_present_f2 || that_present_f2) {
      if (!(this_present_f2 && that_present_f2))
        return false;
      if (!this.f2.equals(that.f2))
        return false;
    }
    boolean this_present_f3 = true && this.isSetF3();
    boolean that_present_f3 = true && that.isSetF3();
    if (this_present_f3 || that_present_f3) {
      if (!(this_present_f3 && that_present_f3))
        return false;
      if (!this.f3.equals(that.f3))
        return false;
    }
    boolean this_present_f4 = true && this.isSetF4();
    boolean that_present_f4 = true && that.isSetF4();
    if (this_present_f4 || that_present_f4) {
      if (!(this_present_f4 && that_present_f4))
        return false;
      if (!this.f4.equals(that.f4))
        return false;
    }
    return true;
  }


  @Override
  public int hashCode() {
    int hashCode = 1;
    if (isSetF1()) {
      hashCode = 31 * hashCode + f1.hashCode();
    }
    if (isSetF2()) {
      hashCode = 31 * hashCode + f2.hashCode();
    }
    if (isSetF3()) {
      hashCode = 31 * hashCode + f3.hashCode();
    }
    if (isSetF4()) {
      hashCode = 31 * hashCode + f4.hashCode();
    }
    return hashCode;
  }

  public int compareTo(ThriftMsgV5 other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;
    ThriftMsgV5 typedOther = (ThriftMsgV5)other;

    lastComparison = Boolean.valueOf(isSetF1()).compareTo(typedOther.isSetF1());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetF1()) {
      lastComparison = TBaseHelper.compareTo(this.f1, typedOther.f1);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetF2()).compareTo(typedOther.isSetF2());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetF2()) {
      lastComparison = TBaseHelper.compareTo(this.f2, typedOther.f2);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetF3()).compareTo(typedOther.isSetF3());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetF3()) {
      lastComparison = TBaseHelper.compareTo(this.f3, typedOther.f3);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetF4()).compareTo(typedOther.isSetF4());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetF4()) {
      lastComparison = TBaseHelper.compareTo(this.f4, typedOther.f4);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }


  public void read(TProtocol iprot) throws TException {
    TField field;
    iprot.readStructBegin();
    while (true)
    {
      field = iprot.readFieldBegin();
      if (field.type == TType.STOP) {
        break;
      }
      switch (field.id) {
        case 1: // F1
          this.f1 = iprot.readString();
          break;
        case 2: // F2
          this.f2 = iprot.readString();
          break;
        case 3: // F3
          this.f3 = iprot.readString();
          break;
        case 4: // F4
          this.f4 = iprot.readString();
          break;
        default:
          TProtocolUtil.skip(iprot, field.type);
      }
      iprot.readFieldEnd();
    }
    iprot.readStructEnd();

    // check for required fields of primitive type, which can't be checked in the validate method
    validate();
  }

  public void write(TProtocol oprot) throws TException {
    validate();
    
    oprot.writeStructBegin(STRUCT_DESC);
    if (this.f1 != null) {
      oprot.writeFieldBegin(F1_FIELD_DESC);
      oprot.writeString(this.f1);
      oprot.writeFieldEnd();
    }
    if (this.f2 != null) {
      if (isSetF2()) {
        oprot.writeFieldBegin(F2_FIELD_DESC);
        oprot.writeString(this.f2);
        oprot.writeFieldEnd();
      }
    }
    if (this.f3 != null) {
      if (isSetF3()) {
        oprot.writeFieldBegin(F3_FIELD_DESC);
        oprot.writeString(this.f3);
        oprot.writeFieldEnd();
      }
    }
    if (this.f4 != null) {
      if (isSetF4()) {
        oprot.writeFieldBegin(F4_FIELD_DESC);
        oprot.writeString(this.f4);
        oprot.writeFieldEnd();
      }
    }
    oprot.writeFieldStop();
    oprot.writeStructEnd();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("ThriftMsgV5(");
    boolean first = true;
    sb.append("f1:");
    if (this.f1 == null) {
      sb.append("null");
    } else {
      sb.append(this.f1);
    }
    first = false;
    if (isSetF2()) {
      if (!first) sb.append(", ");
      sb.append("f2:");
      if (this.f2 == null) {
        sb.append("null");
      } else {
        sb.append(this.f2);
      }
      first = false;
      }
    if (isSetF3()) {
      if (!first) sb.append(", ");
      sb.append("f3:");
      if (this.f3 == null) {
        sb.append("null");
      } else {
        sb.append(this.f3);
      }
      first = false;
      }
    if (isSetF4()) {
      if (!first) sb.append(", ");
      sb.append("f4:");
      if (this.f4 == null) {
        sb.append("null");
      } else {
        sb.append(this.f4);
      }
      first = false;
      }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws TException {
    // check for required fields
    if (f1 == null) {
      throw new TProtocolException("Required field 'f1' was not present! Struct: " + toString());
    }
  }
}

