package org.thornfish.androidlibrary.wxapi;

//public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

//	private static final String TAG = "WXPayEntryActivity";
//
//	private IWXAPI api;
//
//	@Override
//	public void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.pay_result);
//
//		api = WXAPIFactory.createWXAPI(this, Constants.APP_ID);
//		api.handleIntent(getIntent(), this);
//		StatusBarUtil.setTransparent(this);
//	}
//
//	@Override
//	protected void onNewIntent(Intent intent) {
//		super.onNewIntent(intent);
//		setIntent(intent);
//		api.handleIntent(intent, this);
//	}
//
//	@Override
//	public void onReq(BaseReq req) {
//	}
//
//	@Override
//	public void onResp(BaseResp resp) {
//		Log.e(TAG, "onPayFinish, errCode = " + resp.errCode+"---"+resp.toString());
//
//		if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
//			final String trade =  SpUtils.getStringSP(WXPayEntryActivity.this,"user","trade");
//			new MyAlertDialog.Builder(this, 0).setTitle("提示")
//					.setMessage("是否支付完成？")
//					.setPositiveButton("确定", new View.OnClickListener() {
//						@Override
//						public void onClick(View v) {
////							HttpPostGet.POST_PAYRESULT(WXPayEntryActivity.this, trade, payback);
//						}
//					}, true)
//					.setNegativeButton("取消", new View.OnClickListener() {
//						@Override
//						public void onClick(View v) {
////							HttpPostGet.POST_PAYRESULT(WXPayEntryActivity.this, trade, payback);
//						}
//					}, true)
//					.show();
//		}
//	}
//
//	private Callback payback = new BaseCallback(this) {
//
//		@Override
//		protected void onContent(String content) throws Exception {
//			if (content != null) {
//				Log.e("CurrencyActivity", content);
//				if (JSONObject.parseObject(content).getString("respcode").equals("0")) {
//
//						Toast.makeText(WXPayEntryActivity.this, JSONObject.parseObject(content).getString("respMsg"), Toast.LENGTH_SHORT).show();
//						SpUtils.saveStringSP(WXPayEntryActivity.this,"user","trade","");
//						//						EventBus.getDefault().post(new IndexPwd(7));
//						WXPayEntryActivity.this.finish();
//				} else {
//					Toast.makeText(WXPayEntryActivity.this, JSONObject.parseObject(content).getString("respMsg"), Toast.LENGTH_SHORT).show();
//					WXPayEntryActivity.this.finish();
//					SpUtils.saveStringSP(WXPayEntryActivity.this,"user","trade","");
//				}
//			}
//		}
//
//		@Override
//		public void onError(Call call, Exception e, int i) {
//			Log.e("PersonalActivity", "e:" + e);
//			Toast.makeText(WXPayEntryActivity.this, getString(R.string.pay_error), Toast.LENGTH_SHORT).show();
//			SpUtils.saveStringSP(WXPayEntryActivity.this,"user","trade","");
//			WXPayEntryActivity.this.finish();
//		}
//
//		@Override
//		public void onResponse(String o, int i) {
//
//		}
//	};
//
//}