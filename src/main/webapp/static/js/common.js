
function doUploadFile(configs,callback) {
	var settings={
			'formData'     : configs.formData,
			'buttonText' : configs.buttonText,
			'fileDesc':configs.fileDesc,
			'fileExt':configs.fileExt,
			'successTimeout':999999,
			'width' : 120,  
            'auto':false,  
            'fileObjName'   : configs.fileObjName,
			'swf'      : configs.resource+'/uploadify/uploadify.swf',
			'uploader' : configs.uploader,
			'onUploadSuccess':function(file, data, result){
				if(result){
					callback($.parseJSON(data));
			    }else{
			    	layer.msg("上传失败");
			    }
				
			},
			onUploadStart:function(file){
				if(typeof(configs.getFormData)!="undefined"){
					setFormData(configs.getFormData());
				}
			    $("#btn_upload").uploadify("settings", "formData", _formData);
			}
	};
	
	$(configs.fileInput).uploadify(settings);
	$(configs.uploadBtn).click(function(){
		   $(configs.fileInput).uploadify("upload","*");
	});
	$(configs.cancelBtn).click(function(){
		   $(configs.fileInput).uploadify("stop");
	});
}
var _formData={};
function setFormData(formData){
	_formData=formData;
}
