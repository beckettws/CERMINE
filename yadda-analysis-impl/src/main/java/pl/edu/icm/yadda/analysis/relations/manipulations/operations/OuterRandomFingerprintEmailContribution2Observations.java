//package pl.edu.icm.yadda.analysis.relations.manipulations.operations;
//
//import java.util.Date;
//import java.util.LinkedList;
//import java.util.ListIterator;
//import java.util.Map;
//import java.util.Random;
//
//import org.openrdf.model.Resource;
//import org.openrdf.model.URI;
//import org.openrdf.model.ValueFactory;
//import org.openrdf.query.BindingSet;
//import org.openrdf.query.QueryLanguage;
//import org.openrdf.query.TupleQuery;
//import org.openrdf.query.TupleQueryResult;
//import org.openrdf.repository.Repository;
//
//import pl.edu.icm.yadda.analysis.relations.constants.RelConstants;
//
//public class OuterRandomFingerprintEmailContribution2Observations implements Operation{
//
//	@Override
//	public Object execute(Object repository, Map<String, Object> operationParam) {
//		double pa = 100 * (Double) operationParam.get("random");
//		int part = (int) pa;
//		Random rnd = new Random();
//		
//		Repository repo = (Repository) repository;
//    	try {
//    		ValueFactory vf = repo.getConnection().getValueFactory();
//    		
//    		String contribQuery = "" +
//			" Select distinct c,p,e" +
//			" from" +
//			" {c} <"+RelConstants.RL_IS_PERSON+"> {p}," +
//			" {c} <"+RelConstants.RL_CONTACT_EMAIL+"> {e}" +
//			" where" +
//			" e!=\"\"" +
//			" AND p!=<http://yadda.icm.edu.pl/person#zbl#->" +
//			"";
//			
//    		
//    		TupleQuery query = repo.getConnection().prepareTupleQuery(QueryLanguage.SERQL, contribQuery);
//			TupleQueryResult res = query.evaluate();
//			LinkedList<String> tempAllContribList = new LinkedList<String>();
//			while(res.hasNext()){
//				BindingSet bs = res.next();
//				tempAllContribList.add(bs.getValue("c").toString());
//			}
//			res.close();
//		
//			System.out.println("There are "+tempAllContribList.size()+" contributions to cross");
//			
//			int counted=0;
//			
//			int minorTotal = tempAllContribList.size();
//			
//			int  total = (int) ( minorTotal * minorTotal * pa); 
//			
//			int observationNumber = 0;
//			
//			ListIterator<String> outer = tempAllContribList.listIterator();
//			for(int i=0;outer.hasNext();i++){
//				int have = i*tempAllContribList.size();
//				System.out.println(have+"/"+total + "\t"+(have*100/(double)total)+"%\t"+new Date());
//				
//				String contribA = outer.next();
//				if(rnd.nextInt(100)>part) continue;
//				
//				counted+= (minorTotal-i);
//				System.out.println("counted: "+counted);
//				System.out.println("counted/pow(minorTotal,2): "+(counted/(minorTotal*minorTotal)));
//				System.out.println("counted/total: "+counted/total);
//				
//				ListIterator<String> inner = tempAllContribList.listIterator(outer.nextIndex());
//				for(int j=i+1;inner.hasNext();j++){
//					String contribB = inner.next();
//
//					URI observation = vf.createURI(RelConstants.NS_OBSERVATION+observationNumber);
//					
//					repo.getConnection().add(observation, vf.createURI(RelConstants.RL_OBSERVATION_ID), 
//							vf.createLiteral(observationNumber),(Resource)null);
//					repo.getConnection().add(observation, vf.createURI(RelConstants.RL_OBSERVATION_CONTRIBUTOR), 
//							vf.createURI(contribA),(Resource)null);
//					repo.getConnection().add(observation, vf.createURI(RelConstants.RL_OBSERVATION_CONTRIBUTOR), 
//							vf.createURI(contribB),(Resource)null);
//					observationNumber++;
//				}
//			}
//			return null;	
//    	} catch (Exception e) {
//			return e;
//    	}
//	}
//}