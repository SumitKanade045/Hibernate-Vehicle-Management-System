package com.app.sericeImpl;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.app.config.HibernateUtil;
import com.app.exception.InsuranceNotFoundException;
import com.app.exception.ServiceRecordNotFoundException;
import com.app.exception.VehicleNotFoundException;
import com.app.model.Insurance;
import com.app.model.ServiceRecord;
import com.app.model.Vehicle;
import com.app.serviceI.Requirements;

public class RequirementsImpl implements Requirements {

	Scanner sc = new Scanner(System.in);

	Session session = HibernateUtil.getSessionFactory().openSession();

	@Override
	public void insertData() {

		Session session = HibernateUtil.getSessionFactory().openSession();

		Vehicle v = new Vehicle();

		Insurance i1 = new Insurance();

		System.out.println("------------------Enter Vehicle Details------------------");

		System.out.println("Enter Vehicle Name: ");
		v.setVehicleName(sc.next());

		System.out.println("Enter Vehicle Number: ");
		v.setVehicleNumber(sc.next());

		System.out.println("Enter Vehicle Type: ");
		v.setVehicleType(sc.next());

		System.out.println("Enter Vehicle Price: ");
		v.setPrice(sc.nextDouble());

		System.out.println("------------------Enter Insurance Details------------------");

		System.out.println("Enter Insurance Company Name: ");
		i1.setInsuranceCompany(sc.next());

		System.out.println("Enter Insurance Policy Number: ");
		i1.setPolicyNumber(sc.next());

		System.out.println("Enter Insurance Expiry Date: ");
		i1.setExpiryDate(sc.next());

		System.out.println("------------------Enter ServiceRecord Details------------------");

		System.out.println("How Many ServiceRecord You Want To Add: ");
		int n = sc.nextInt();

		for (int i = 1; i <= n; i++) {

			ServiceRecord sr = new ServiceRecord();

			System.out.println(
					"--------------------------Enter " + i + " ServiceRecord Details--------------------------");

			System.out.println("Enter Service Date: ");
			sr.setServiceDate(sc.next());

			System.out.println("Enter Service Type: ");
			sr.setServiceType(sc.next());

			System.out.println("Enter Service Cost: ");
			sr.setServiceCost(sc.nextDouble());

			v.getServiceRecords().add(sr);

		}

		v.setInsurance(i1);
		Transaction tx = session.beginTransaction();

		session.persist(v);
		tx.commit();

	}

	@Override
	public void getSingledata() {

		System.out.println("Enter Vehicle Id To get Details of Vehicle: ");
		int id = sc.nextInt();

		Vehicle v = session.find(Vehicle.class, id);

		if (v != null) {

			System.out.println("------------Vehicle Details------------");

			System.out.println("Vehicle Id           : " + v.getVehicleId());
			System.out.println("Vehicle Name         : " + v.getVehicleName());
			System.out.println("Vehicle Number       : " + v.getVehicleNumber());
			System.out.println("Vehicle Type         : " + v.getVehicleType());
			System.out.println("Vehicle Price        : " + v.getPrice());

			Insurance i = v.getInsurance();

			if (i != null) {

				System.out.println("------------Insurance Details------------");

				System.out.println("Insurance Id          : " + i.getInsuranceId());
				System.out.println("Insurance Company     : " + i.getInsuranceCompany());
				System.out.println("Insurance Number      : " + i.getPolicyNumber());
				System.out.println("Insurance Expiry Date : " + i.getExpiryDate());
			}

			else {

				throw new InsuranceNotFoundException("Insurance Not Found For This Vehicle !");
			}

			List<ServiceRecord> serviceRecord = v.getServiceRecords();

			System.out.println("------------ServiceRecord Details------------");

			if (!serviceRecord.isEmpty()) {

				for (ServiceRecord sr : serviceRecord) {

					System.out.println("Service Id            : " + sr.getServiceId());
					System.out.println("Service Date          : " + sr.getServiceDate());
					System.out.println("Service Type          : " + sr.getServiceType());
					System.out.println("Service Cost          : " + sr.getServiceCost());
				}

			} else {

				throw new ServiceRecordNotFoundException("Service Record Not Found For This Vehicle !");
			}

		} else {

			throw new VehicleNotFoundException("Vehicle Not Found ! for this id.");

		}

	}

	@Override
	public void getAllData() {

		Query<Vehicle> query = session.createQuery("From Vehicle", Vehicle.class);

		List<Vehicle> set = query.getResultList();

		if (!set.isEmpty()) {

			for (Vehicle v : set) {

				System.out.println("------------Vehicle Details------------");

				System.out.println("Vehicle Id            : " + v.getVehicleId());
				System.out.println("Vehicle Name          : " + v.getVehicleName());
				System.out.println("Vehicle Number        : " + v.getVehicleNumber());
				System.out.println("Vehicle Type          : " + v.getVehicleType());
				System.out.println("Vehicle Price         : " + v.getPrice());

				Insurance i = v.getInsurance();

				if (i != null) {

					System.out.println("------------Insurance Details------------");

					System.out.println("Insurance Id          : " + i.getInsuranceId());
					System.out.println("Insurance Company     : " + i.getInsuranceCompany());
					System.out.println("Insurance Number      : " + i.getPolicyNumber());
					System.out.println("Insurance Expiry Date : " + i.getExpiryDate());
				}

				else {

					throw new InsuranceNotFoundException("Insurance Not Found For This Vehicle !");

				}
				List<ServiceRecord> serviceRecord = v.getServiceRecords();

				if (serviceRecord != null && !serviceRecord.isEmpty()) {

					System.out.println("------------ServiceRecord Details------------");

					for (ServiceRecord sr : serviceRecord) {

						System.out.println("Service Id            : " + sr.getServiceId());
						System.out.println("Service Date          : " + sr.getServiceDate());
						System.out.println("Service Type          : " + sr.getServiceType());
						System.out.println("Service Cost          : " + sr.getServiceCost());
					}

				} else {

					throw new ServiceRecordNotFoundException("Service Record Not Found For This Vehicle !");

				}

			}

		} else {
			throw new VehicleNotFoundException("Vehicle Not Found ! for this id.");

		}

	}

	@Override
	public void updateData() {

		System.out.println("Enter Id To Update Vechicle: ");
		int id = sc.nextInt();
		Vehicle v = session.find(Vehicle.class, id);

		if (v != null) {

			System.out.println("What will you wnat to update");

			System.out.println("1.For Vehicle \n2.For Insurance \n3.For Service Record\n4.All Details Update\n5.Exit");

			int n = sc.nextInt();

			switch (n) {

			case 1:

				System.out.println("------------------Enter Vehicle Details------------------");

				v.setVehicleId(id);

				System.out.println("Enter Vehicle Name: ");
				v.setVehicleName(sc.next());

				System.out.println("Enter Vehicle Number: ");
				v.setVehicleNumber(sc.next());

				System.out.println("Enter Vehicle Type: ");
				v.setVehicleType(sc.next());

				System.out.println("Enter Vehicle Price: ");
				v.setPrice(sc.nextDouble());

				Transaction transaction = session.beginTransaction();
				session.merge(v);
				transaction.commit();

				break;

			case 2:

				Insurance i1 = v.getInsurance();

				System.out.println("------------------Enter Insurance Details------------------");

				System.out.println("Enter Insurance Company Name: ");
				i1.setInsuranceCompany(sc.next());

				System.out.println("Enter Insurance Policy Number: ");
				i1.setPolicyNumber(sc.next());

				System.out.println("Enter Insurance Expiry Date: ");
				i1.setExpiryDate(sc.next());

				Transaction transaction1 = session.beginTransaction();
				session.merge(v);
				transaction1.commit();

				break;

			case 3:

				List<ServiceRecord> sr1 = v.getServiceRecords();

				for (ServiceRecord sr : sr1) {

					System.out.println("Enter Service Date: ");
					sr.setServiceDate(sc.next());

					System.out.println("Enter Service Type: ");
					sr.setServiceType(sc.next());

					System.out.println("Enter Service Cost: ");
					sr.setServiceCost(sc.nextDouble());

//					v.getServiceRecords().add(sr);

				}

				Transaction transaction2 = session.beginTransaction();

				session.merge(v);
				transaction2.commit();

				break;

			case 4:

				System.out.println("------------------Enter Vehicle Details------------------");

				System.out.println("Enter Vehicle Name: ");
				v.setVehicleName(sc.next());

				System.out.println("Enter Vehicle Number: ");
				v.setVehicleNumber(sc.next());

				System.out.println("Enter Vehicle Type: ");
				v.setVehicleType(sc.next());

				System.out.println("Enter Vehicle Price: ");
				v.setPrice(sc.nextDouble());

				Insurance i11 = v.getInsurance();

				System.out.println("------------------Enter Insurance Details------------------");

				System.out.println("Enter Insurance Company Name: ");
				i11.setInsuranceCompany(sc.next());

				System.out.println("Enter Insurance Policy Number: ");
				i11.setPolicyNumber(sc.next());

				System.out.println("Enter Insurance Expiry Date: ");
				i11.setExpiryDate(sc.next());

				System.out.println("------------------Enter ServiceRecord Details------------------");

				List<ServiceRecord> serviceRecords = v.getServiceRecords();

				for (ServiceRecord sr11 : serviceRecords) {

					System.out.println("Enter Service Date: ");
					sr11.setServiceDate(sc.next());

					System.out.println("Enter Service Type: ");
					sr11.setServiceType(sc.next());

					System.out.println("Enter Service Cost: ");
					sr11.setServiceCost(sc.nextDouble());

//					v.getServiceRecords().add(sr11);

				}

				Transaction transaction3 = session.beginTransaction();

				session.merge(v);

				transaction3.commit();

				break;

			case 5:

				System.exit(0);
				break;

			default:

				System.out.println("Enter Valid Choise");

			}

		} else {

			throw new VehicleNotFoundException("Vehicle Not Found ! for this id.");
		}

	}

	@Override
	public void deleteData() {

		System.out.println("Enter Id to delete vehicle: ");
		int id = sc.nextInt();

		Vehicle vehicle = session.find(Vehicle.class, id);

		if (vehicle != null) {

			session.remove(vehicle);

			Transaction transaction1 = session.beginTransaction();
			transaction1.commit();

			System.out.println("Vehicle Deleted Successfully !");

		} else {

			throw new VehicleNotFoundException("Vehicle Not Found ! for this id.");

		}

	}

	@Override
	public void deleteAllData() {

		Transaction transaction1 = session.beginTransaction();

		Query<Vehicle> query = session.createQuery("from Vehicle", Vehicle.class);

		List<Vehicle> list = query.getResultList();

		if (!list.isEmpty()) {

			for (Vehicle v : list) {

				session.remove(v);
			}

			transaction1.commit();

			System.out.println("All Data Deleted Successfully");
		}

		else {

			throw new VehicleNotFoundException("Vehicle Not Found ! for this id.");
		}
	}

}
