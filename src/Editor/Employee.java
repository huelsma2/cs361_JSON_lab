package Editor;

/**
 * Employee class which holds first name, last name, phone number, and department
 * @author Andrew Huelsman
 *
 */
public class Employee {

	private String _fname, _lname, _phone, _department;
	
	public Employee(String fname, String lname, String phone, String dept)
	{
		this._fname = fname;
		this._lname = lname;
		this._phone = phone;
		this._department = dept;
	}

	public void set_fname(String _fname) {
		this._fname = _fname;
	}

	public void set_lname(String _lname) {
		this._lname = _lname;
	}

	public void set_phone(String _phone) {
		this._phone = _phone;
	}

	public void set_department(String _department) {
		this._department = _department;
	}
	
	
	@Override
	public String toString()
	{
		return _lname + ", " + _fname + " " + _phone + " " + _department;
	}
}
