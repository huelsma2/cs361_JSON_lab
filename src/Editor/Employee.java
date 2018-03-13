package Editor;

/**
 * Employee class which holds first name, last name, phone number, and department
 * @author Andrew Huelsman
 *
 */
public class Employee {

	private String _fname, _lname, _phone, _department, _title, _gender;

	public String get_lname() {
		return _lname;
	}
	public String get_fname() {
		return _fname;
	}

	public Employee(String fname, String lname, String dept, String phone, String gender, String title)
	{
		this._fname = fname;
		this._lname = lname;
		this._phone = phone;
		this._department = dept;
		this._gender=gender;
		this._title=title;
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
		return _title + " " + _lname + ", " + _fname + " (" + _gender + ") in " + _department + " Contact: "+ _phone;
	}
}
