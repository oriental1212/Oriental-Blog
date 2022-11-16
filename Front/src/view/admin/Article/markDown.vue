
<template>
  <div id="vditor"></div>
</template>
<script>
import Vditor from "vditor"
import { ElNotification } from 'element-plus';

import "vditor/dist/index.css"
export default {
  props: {
    value: {
      type: String,
      default: ''
    },
    id: {
      type: String,
      required: false,
      default () {
        return 'markdown-editor-' + +new Date() + ((Math.random() * 1000).toFixed(0) + '')
      }
    },
    isHideTools: {
      type: Boolean,
      required: false,
      default: false
    },
    isPin: {
      type: Boolean,
      required: false,
      default: true
    },
    height: {
      type: String,
      required: false,
      default: '300px'
    },
    width: {
      type: String,
      required: false,
      default: "auto"
    },
    mode: {
      type: String,
      required: false,
      default: 'sv'
    },
  },

  data () {
    return {
      contentEditor: ""
    }
  },
  //mounted
  mounted () {
    this.contentEditor = new Vditor("vditor", {
      height: this.height,
      width: this.width,
      toolbarConfig: {
        pin: this.isPin,
        hide: this.isHideTools,
      },
      cache: {
        enable: false
      },
      toolbar: [
        "emoji",
        "headings",
        "bold",
        "italic",
        "strike",
        "link",
        "|",
        "list",
        "ordered-list",
        "check",
        "outdent",
        "indent",
        "|",
        "quote",
        "line",
        "code",
        "inline-code",
        "insert-before",
        "insert-after",
        "|",
        //"upload",
        // "record",
        { //自定义上传
          hotkey: "",
          name: "upload",
          // tipPosition: "s",
          tip: "上传图片",
          className: "right",
        },

        "table",
        "|",
        "undo",
        "redo",
        "|",
        // "fullscreen",
        "edit-mode",
        {
          name: "more",
          toolbar: [
            //"both",
            "code-theme",
            "content-theme",
            "export",
            "outline",
            "preview",
            //"devtools",
            // "info",
            //"help",
          ],
        },
      ],
      after: () => {
        this.contentEditor.setValue(this.value)
      },
      mode: this.mode,
      preview: {
        mode: "both",
        actions: []
      },
      //这里写上传
      upload: {
        accept: 'image/jpg, image/jpeg, image/png',//规定上传的图片格式
        url: "/api/admin/uploadFile?type=99",//请求的接口
        multiple: false,
        fieldName: 'file',
        max: 2 * 1024 * 1024,//上传图片的大小
        // extraData: { 'access_token': this.token }, //为 FormData 添加额外的参数
        linkToImgUrl: "/api/admin/uploadFile?type=99",
        filename (name) {
          return name.replace(/[^(a-zA-Z0-9\u4e00-\u9fa5\.)]/g, "")
              .replace(/[\?\\/:|<>\*\[\]\(\)\$%\{\}@~]/g, "")
              .replace("/\\s/g", "");
        },
        validate (files) {
          const isLt2M = files[0].size / 1024 / 1024 < 2
          if (!isLt2M) {
            Message({
              message: "上传图片大小不能超过 10MB!",
              type: 'error',
            })
            //this.$message.error('')
          }
        },
        //上传图片回显处理
        format (files, responseText) {
          var self = this;
          var data = JSON.parse(responseText)
          //上传图片请求状态
          if (data.status) {
            let filName = data.msg
            let lastTipNum = filName.substr(filName.lastIndexOf('/', filName.lastIndexOf('/') - 1) + 1);
            console.log("lastTipNum", lastTipNum)
            let index = lastTipNum.lastIndexOf("\/");
            let succ = {}
            succ[filName] = "/api" + data.data
            //图片回显
            return JSON.stringify({
              data: {
                errFiles: [],
                succMap: succ
              }
            })
          } else {
            ElNotification({
              message: "图片上传失败",
              type: 'error',
            })
          }

        },
        error (msg) {
          console.log(msg + "上传失败了")
        },
      }
    })
  },
  methods: {
    getValue () {
      return this.contentEditor.getValue();     //获取 Markdown 内容
    },
    getHTML () {
      return this.contentEditor.getHTML();      //获取 HTML 内容
    },
    setValue (value) {
      return this.contentEditor.setValue(value);     //设置 Markdown 内容
    },
    disabled () {
      return this.contentEditor.disabled();     //设置 只读
    },
    toPreview () {
      var evt = document.createEvent('Event');
      evt.initEvent('click', true, true);
      this.contentEditor.vditor.toolbar.elements.preview.firstElementChild.dispatchEvent(evt);
    },
  },

}
</script>