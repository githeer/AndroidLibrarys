package org.thornfish.androidlibrary.utils;



public class TDealPullData{
//	@SuppressWarnings({ "unchecked", "rawtypes" })
//	public static <T> PublicEntity<T> onDealPullData(Context context,TPullData pullData,String arg0, int Loadtype,PublicEntity<T> orderEntity,BaseListAdapter<T> recyclerAdapter){
//		 PublicEntity<T> gson = MyGson.Gson(context, arg0, orderEntity);
//		if (gson!=null) {
//			if (gson.getRespCode().equals("0")) {
//				if (Loadtype==pullData.LoadMore) {
//					if (gson.getData()!=null&&gson.getData().size()>0) {
//						recyclerAdapter.setAddData((Collection<T>) gson.getData());
//					}
//				}else if(Loadtype==pullData.Refresh){
//					recyclerAdapter.setDatas((Collection<T>) gson.getData());
//				}else{
//
//				}
//				pullData.PullEnd(Loadtype, gson.getData().size()>0? true:false);
//				return gson;
//			}
//		}
//		pullData.PullEnd(Loadtype, false);
//		pullData=null;
//		context=null;
//		orderEntity=null;
//		recyclerAdapter=null;
//		return gson;
//	}
}
