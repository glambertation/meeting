webpackJsonp([1], {
    0: function (t, e) {
    }, "2gX3": function (t, e) {
    }, "58Rz": function (t, e) {
    }, "8G3d": function (t, e) {
    }, "9Iw7": function (t, e) {
    }, "9n10": function (t, e) {
    }, B9tX: function (t, e) {
    }, C6QW: function (t, e) {
    }, IJNH: function (t, e) {
    }, LN4N: function (t, e) {
    }, MjqY: function (t, e) {
    }, NHnr: function (t, e, n) {
        "use strict";
        Object.defineProperty(e, "__esModule", {value: !0});
        var a = n("7+uW"), s = {
                render: function () {
                    var t = this.$createElement, e = this._self._c || t;
                    return e("div", [e("router-view")], 1)
                }, staticRenderFns: []
            }, i = n("VU/8")({}, s, !1, null, null, null).exports, o = n("/ocq"), c = (n("Beyc"), n("V3P/")),
            r = (n("D5Mw"), n("IlMV")), l = (n("CkVG"), n("0GVE")), d = (n("btEx"), n("7sBf")),
            u = (n("dKz4"), n("m/wa")), v = (n("Qj71"), n("hOIf")), h = (n("jPjZ"), n("QzVA")),
            f = (n("fHJy"), n("4nqm")), m = (n("gurA"), n("+kBM")), g = (n("OWWv"), n("Jyaz")),
            j = (n("G13B"), n("/bFu")), p = (n("QQuP"), n("J65+")), _ = {
                name: "TheHeading",
                components: {ALayoutHeader: g.a.Header, AIcon: f.a, ARow: p.a, ACol: j.a},
                data: function () {
                    return {nowDate: "", nowTime: "", nowWeek: ""}
                },
                methods: {
                    currentTime: function () {
                        setInterval(this.getDate, 500)
                    }, getDate: function () {
                        var t = (new Date).getFullYear(), e = (new Date).getMonth() + 1, n = (new Date).getDate(),
                            a = (new Date).getDay(), s = (new Date).getHours(),
                            i = (new Date).getMinutes() < 10 ? "0" + (new Date).getMinutes() : (new Date).getMinutes();
                        this.nowWeek = 1 === a ? "星期一" : 2 === a ? "星期二" : 3 === a ? "星期三" : 4 === a ? "星期四" : 5 === a ? "星期五" : 6 === a ? "星期六" : "星期日", this.nowTime = s + " : " + i, this.nowDate = t + "-" + e + "-" + n
                    }
                },
                mounted: function () {
                    this.currentTime()
                }
            }, b = {
                render: function () {
                    var t = this, e = t.$createElement, n = t._self._c || e;
                    return n("a-layout-header", [n("a-row", {
                        staticClass: "antRow",
                        attrs: {type: "flex", justify: "space-around", align: "middle", gutter: 10}
                    }, [n("a-col", {attrs: {span: 2}}, [n("span", {staticClass: "nowTime"}, [n("a-icon", {attrs: {type: "clock-circle"}}), t._v(" " + t._s(t.nowTime))], 1)]), t._v(" "), n("a-col", {attrs: {span: 2}}, [n("button", {staticClass: "btn"}, [t._v("行车计划")])]), t._v(" "), n("a-col", {attrs: {span: 2}}, [n("button", {staticClass: "btn"}, [t._v("作业管理")])]), t._v(" "), n("a-col", {attrs: {span: 2}}, [n("button", {staticClass: "btn"}, [t._v("乘客管理")])]), t._v(" "), n("a-col", {
                        staticClass: "title2",
                        attrs: {span: 8}
                    }, [n("label", [t._v("宁波地铁")])]), t._v(" "), n("a-col", {attrs: {span: 2}}, [n("button", {staticClass: "btn"}, [t._v("综合监控")])]), t._v(" "), n("a-col", {attrs: {span: 2}}, [n("button", {staticClass: "btn"}, [t._v("资产管理")])]), t._v(" "), n("a-col", {attrs: {span: 2}}, [n("button", {staticClass: "btn"}, [t._v("应急管理")])]), t._v(" "), n("a-col", {
                        staticClass: "weekAndDate",
                        attrs: {span: 2, type: "flex"}
                    }, [n("a-col", {attrs: {span: 24}}, [t._v("\n        " + t._s(t.nowWeek) + "\n      ")]), t._v(" "), n("a-col", {attrs: {span: 24}}, [t._v("\n        " + t._s(t.nowDate) + "\n      ")])], 1)], 1)], 1)
                }, staticRenderFns: []
            };
        var y = n("VU/8")(_, b, !1, function (t) {
                n("Xsl4")
            }, "data-v-f5f53c58", null).exports, w = n("mtWM"), k = n.n(w),
            C = [{title: "队列", dataIndex: "id", key: "id", scopedSlots: {customRender: "id"}}, {
                title: "终端名称",
                dataIndex: "machineNumber",
                key: "machineNumber",
                ellipsis: !0
            }, {title: "状态", dataIndex: "pause", key: "pause", ellipsis: !0}, {
                title: "位置",
                dataIndex: "machineZone",
                key: "machineZone",
                ellipsis: !0
            }, {title: "", key: "action", scopedSlots: {customRender: "action"}}], x = {
                name: "Home",
                components: {
                    TheHeading: y,
                    ALayout: g.a,
                    ALayoutSider: g.a.Sider,
                    ALayoutContent: g.a.Content,
                    AButton: m.a,
                    AIcon: f.a,
                    ATable: h.a,
                    ADropdown: v.a,
                    AMenu: u.a,
                    AMenuItem: u.a.Item,
                    ATextarea: d.a.TextArea,
                    ADivider: l.a,
                    AModal: r.a,
                    ATransfer: c.a
                },
                data: function () {
                    return {
                        location: "",
                        machineId: "",
                        name: "",
                        sex: "",
                        identity: "",
                        data: [],
                        columns: C,
                        visible: !1,
                        mockData: [],
                        targetKeys: [],
                        account: window.sessionStorage.account || "admin",
                        isJoin: !1,
                        userList: [],
                        roomid: "webrtc_1v1",
                        isCall: !1,
                        isToPeer: !1,
                        peer: null,
                        offerOption: {offerToReceiveAudio: 1, offerToReceiveVideo: 1},
                        ModalText: "",
                        msgVisible: !1,
                        confirmLoading: !1
                    }
                },
                methods: {
                    qh: function () {
                        event.currentTarget.setAttribute("class", "hidden"), event.currentTarget.nextElementSibling.setAttribute("class", "ant-btn ant-btn-danger ant-btn-sm")
                    }, hq: function () {
                        event.currentTarget.setAttribute("class", "hidden"), event.currentTarget.previousElementSibling.setAttribute("class", "ant-btn ant-btn-primary ant-btn-sm")
                    }, getHomeInfo: function () {
                        k.a.get("../../static/mock/index.json").then(this.getHomeInfoSucc)
                    }, getHomeInfoSucc: function (t) {
                        if ((t = t.data).ret && t.data) {
                            var e = t.data;
                            this.location = e.location, this.machineId = e.machineId, this.name = e.name, this.sex = e.sex, this.identity = e.identity, this.data = e.Ask_queue, this.mockData = e.userList, this.ModalText = e.event_handle.handleResult
                        }
                        console.log(t)
                    }, handleMenuClick: function (t) {
                        console.log("click", t)
                    }, showModal: function () {
                        this.visible = !0
                    }, handleOk: function (t) {
                        console.log(t), this.msgVisible = !0
                    }, getMock: function () {
                        this.targetKeys = []
                    }, handleChange: function (t, e, n) {
                        console.log(t, e, n), this.targetKeys = t
                    }, showCalling: function () {
                        this.$router.push("/Calling"), window.location.reload()
                    }, msgHandleOk: function (t) {
                        var e = this;
                        this.confirmLoading = !0, setTimeout(function () {
                            e.visible = !1, e.msgVisible = !1, e.confirmLoading = !1
                        }, 1e3)
                    }, handleCancel: function (t) {
                        console.log("Clicked cancel button"), this.msgVisible = !1
                    }
                },
                mounted: function () {
                    this.getHomeInfo(), this.getMock()
                }
            }, z = {
                render: function () {
                    var t = this, e = t.$createElement, n = t._self._c || e;
                    return n("a-layout", {
                        style: {overflow: "auto", height: "100vh"},
                        attrs: {id: "homeLayout"}
                    }, [n("the-heading"), t._v(" "), n("a-layout-content", [n("a-layout", [n("a-layout-sider", {
                        staticClass: "leftSider",
                        style: {width: "28vw", flex: "0 0 auto", "max-width": "28vw", "min-width": "28vw"}
                    }, [n("div", {staticClass: "currentVideo"}, [n("video", {
                        staticClass: "videoA",
                        attrs: {src: "", id: "rtcA", autoplay: ""}
                    }), t._v(" "), n("video", {
                        staticClass: "videoB",
                        attrs: {src: "", id: "rtcB", autoplay: ""}
                    })]), t._v(" "), n("div", {staticClass: "currentVideoInfo"}, [n("table", {staticClass: "currentVideoInfoTable"}, [n("tr", [n("td", [t._v("求助位置:")]), n("td", [t._v(t._s(t.location))])]), t._v(" "), n("tr", [n("td", [t._v("设备编号:")]), n("td", [t._v(t._s(t.machineId))])]), t._v(" "), n("tr", [n("td", [t._v("姓    名:")]), n("td", [t._v(t._s(t.name))])]), t._v(" "), n("tr", [n("td", [t._v("性    别:")]), n("td", [t._v(t._s(t.sex))])]), t._v(" "), n("tr", [n("td", [t._v("身份证号:")]), n("td", [t._v(t._s(t.identity))])])])])]), t._v(" "), n("a-layout-content", {style: {width: "34vw"}}, [n("div", {staticClass: "content"}, [n("h1", {staticClass: "caption"}, [n("a-icon", {attrs: {type: "notification"}}), t._v(" 通话队列")], 1), t._v(" "), n("a-table", {
                        attrs: {
                            columns: t.columns,
                            dataSource: t.data,
                            pagination: {pageSize: 4},
                            rowKey: "id"
                        }, scopedSlots: t._u([{
                            key: "name", fn: function (e) {
                                return n("a", {}, [t._v(t._s(e))])
                            }
                        }, {
                            key: "action", fn: function (e, a) {
                                return n("span", {}, [n("a-button", {
                                    attrs: {type: "primary", size: "small"},
                                    on: {
                                        click: function (e) {
                                            return t.qh()
                                        }
                                    }
                                }, [t._v("通话")]), t._v(" "), n("a-button", {
                                    staticClass: "hidden",
                                    attrs: {type: "danger", size: "small"},
                                    on: {
                                        click: function (e) {
                                            return t.hq()
                                        }
                                    }
                                }, [t._v("挂断")])], 1)
                            }
                        }])
                    }), t._v(" "), n("a-dropdown", [n("a-menu", {
                        attrs: {slot: "overlay"},
                        on: {click: t.handleMenuClick},
                        slot: "overlay"
                    }, [n("a-menu-item", {key: "1"}, [t._v("1st item")]), t._v(" "), n("a-menu-item", {key: "2"}, [t._v("2nd item")]), t._v(" "), n("a-menu-item", {key: "3"}, [t._v("3rd item")])], 1), t._v(" "), n("a-button", {staticClass: "launchPlan"}, [t._v(" 启动预案 "), n("a-icon", {attrs: {type: "down"}})], 1)], 1), t._v(" "), n("a-divider", {staticClass: "adivider"}), t._v(" "), n("h1", {staticClass: "caption"}, [n("a-icon", {attrs: {type: "file-protect"}}), t._v(" 事件记录")], 1), t._v(" "), n("a-textarea", {
                        attrs: {
                            placeholder: "填写记录",
                            rows: 4
                        }
                    }), t._v(" "), n("a-button", {staticClass: "save"}, [t._v("保 存")]), t._v(" "), n("a-divider", {staticClass: "adivider2"}), t._v(" "), n("div", {staticStyle: {"text-align": "left"}}, [n("a-button", {
                        attrs: {type: "primary"},
                        on: {click: t.showModal}
                    }, [t._v("消息派发")]), t._v(" "), n("a-button", {
                        attrs: {type: "primary"},
                        on: {click: t.showCalling}
                    }, [t._v("快速上报")])], 1), t._v(" "), n("div", [n("a-modal", {
                        attrs: {
                            title: "消息派发",
                            okText: "确认",
                            cancelText: "取消"
                        }, on: {ok: t.handleOk}, model: {
                            value: t.visible, callback: function (e) {
                                t.visible = e
                            }, expression: "visible"
                        }
                    }, [n("a-transfer", {
                        attrs: {
                            dataSource: t.mockData,
                            showSearch: "",
                            listStyle: {width: "210px", height: "300px"},
                            locale: {searchPlaceholder: "请输入搜索内容", itemUnit: "项", itemsUnit: "项", notFoundContent: "暂无数据"},
                            targetKeys: t.targetKeys,
                            render: function (t) {
                                return "" + t.name
                            }
                        }, on: {change: t.handleChange}, scopedSlots: t._u([{
                            key: "footer", fn: function (e) {
                                return n("a-button", {
                                    staticStyle: {float: "right", margin: "5px"},
                                    attrs: {size: "small"},
                                    on: {click: t.getMock}
                                }, [t._v("\n                  reload\n                ")])
                            }
                        }])
                    })], 1)], 1), t._v(" "), n("div", [n("a-modal", {
                        attrs: {
                            title: "消息描述",
                            visible: t.msgVisible,
                            "confirm-loading": t.confirmLoading,
                            okText: "发送",
                            cancelText: "取消"
                        }, on: {ok: t.msgHandleOk, cancel: t.handleCancel}
                    }, [n("p", [t._v(t._s(t.ModalText))])])], 1)], 1)]), t._v(" "), n("a-layout-sider", {
                        style: {
                            width: "37vw",
                            flex: "0 0 auto",
                            "max-width": "37vw",
                            "min-width": "37vw"
                        }
                    }, [n("ul", {staticClass: "videoContent"}, [n("li", [n("div", {staticClass: "video2"})]), t._v(" "), n("li", [n("div", {staticClass: "video2"})]), t._v(" "), n("li", [n("div", {staticClass: "video2"})]), t._v(" "), n("li", [n("div", {staticClass: "video2"})]), t._v(" "), n("li", [n("div", {staticClass: "video2"})]), t._v(" "), n("li", [n("div", {staticClass: "video2"})]), t._v(" "), n("li", [n("div", {staticClass: "video2"})]), t._v(" "), n("li", [n("div", {staticClass: "video2"})])])])], 1)], 1)], 1)
                }, staticRenderFns: []
            };
        var M = n("VU/8")(x, z, !1, function (t) {
            n("n4dH")
        }, "data-v-00132bb2", null).exports, T = n("//Fk"), A = n.n(T), H = {
            data: function () {
                return {videoWidth: 3e3, videoHeight: 300, imgSrc: "", thisVideo: null}
            }, methods: {
                getCompetence: function () {
                    var t = this;
                    this.thisVideo = document.getElementById("videoCamera"), void 0 === navigator.mediaDevices && (navigator.mediaDevices = {}), void 0 === navigator.mediaDevices.getUserMedia && (navigator.mediaDevices.getUserMedia = function (t) {
                        var e = navigator.webkitGetUserMedia || navigator.mozGetUserMedia || navigator.getUserMedia;
                        return e ? new A.a(function (n, a) {
                            e.call(navigator, t, n, a)
                        }) : A.a.reject(new Error("getUserMedia is not implemented in this browser"))
                    });
                    var e = {
                        audio: !0,
                        video: {width: this.videoWidth, height: this.videoHeight, transform: "scaleX(-1)"}
                    };
                    navigator.mediaDevices.getUserMedia(e).then(function (e) {
                        "srcObject" in t.thisVideo ? t.thisVideo.srcObject = e : t.thisVideo.src = window.URL.createObjectURL(e), t.thisVideo.onloadedmetadata = function (e) {
                            t.thisVideo.play()
                        }
                    }).catch(function (t) {
                        console.log(t)
                    })
                }, stopNavigator: function () {
                    this.thisVideo.srcObject.getTracks()[0].stop()
                }
            }
        }, S = {
            render: function () {
                var t = this, e = t.$createElement, n = t._self._c || e;
                return n("div", {staticClass: "camera_outer"}, [n("video", {
                    attrs: {
                        id: "videoCamera",
                        width: t.videoWidth,
                        height: t.videoHeight,
                        autoplay: ""
                    }
                }), t._v(" "), n("button", {
                    on: {
                        click: function (e) {
                            return t.getCompetence()
                        }
                    }
                }, [t._v("打开摄像头")]), t._v(" "), n("button", {
                    on: {
                        click: function (e) {
                            return t.stopNavigator()
                        }
                    }
                }, [t._v("关闭摄像头")])])
            }, staticRenderFns: []
        };
        var V = n("VU/8")(H, S, !1, function (t) {
            n("inB+")
        }, "data-v-6ef53035", null).exports, D = {
            name: "Calling",
            components: {
                TheHeading: y,
                ALayout: g.a,
                ALayoutSider: g.a.Sider,
                ALayoutContent: g.a.Content,
                ALayoutFooter: g.a.Footer,
                ARow: p.a,
                ACol: j.a,
                AButton: m.a,
                AModal: r.a,
                ATransfer: c.a
            },
            data: function () {
                return {data: [], visible: !1, targetKeys: [], userList: [], mockData: [], locationHref: ""}
            },
            methods: {
                getUrl: function () {
                    this.$refs.iframe.contentWindow.location.replace("https://localhost:9528/static/index.html")
                }, getHomeInfo: function () {
                    k.a.get("../../static/mock/index.json").then(this.getHomeInfoSucc)
                }, getHomeInfoSucc: function (t) {
                    if ((t = t.data).ret && t.data) {
                        var e = t.data;
                        this.mockData = e.userList
                    }
                    console.log(t)
                }, showModal: function (t) {
                    this.visible = !0, this.locationHref = t
                }, handleOk: function (t) {
                    console.log(t), this.visible = !1, alert(this.locationHref)
                }, getMock: function () {
                    this.targetKeys = []
                }, handleChange: function (t, e, n) {
                    console.log(t, e, n), this.targetKeys = t
                }
            },
            mounted: function () {
                this.getUrl(), this.getHomeInfo(), this.getMock(), window.showModal = this.showModal
            }
        }, I = {
            render: function () {
                var t = this, e = t.$createElement, n = t._self._c || e;
                return n("a-layout", {
                    style: {overflow: "auto", height: "100vh"},
                    attrs: {id: "homeLayout"}
                }, [n("the-heading"), t._v(" "), n("a-layout-content", [n("iframe", {
                    ref: "iframe",
                    staticClass: "ifrm",
                    attrs: {width: "100%", height: "100%", frameborder: "0", scrolling: "auto"}
                }), t._v(" "), n("div", [n("a-modal", {
                    attrs: {title: "快速通话", okText: "立即通话", cancelText: "取消"},
                    on: {ok: t.handleOk},
                    model: {
                        value: t.visible, callback: function (e) {
                            t.visible = e
                        }, expression: "visible"
                    }
                }, [n("a-transfer", {
                    attrs: {
                        dataSource: t.mockData,
                        showSearch: "",
                        listStyle: {width: "210px", height: "300px"},
                        locale: {searchPlaceholder: "请输入搜索内容", itemUnit: "项", itemsUnit: "项", notFoundContent: "暂无数据"},
                        targetKeys: t.targetKeys,
                        render: function (t) {
                            return "" + t.name
                        }
                    }, on: {change: t.handleChange}, scopedSlots: t._u([{
                        key: "footer", fn: function (e) {
                            return n("a-button", {
                                staticStyle: {float: "right", margin: "5px"},
                                attrs: {size: "small"},
                                on: {click: t.getMock}
                            }, [t._v("\n            reload\n          ")])
                        }
                    }])
                })], 1)], 1)]), t._v(" "), n("a-layout-footer", [n("a-row", {staticClass: "icon"}, [n("a-col", {attrs: {span: 7}}), t._v(" "), n("a-col", {attrs: {span: 2}}, [n("span", {staticClass: "iconfont"}, [t._v("")]), n("br"), n("label", [t._v("语音通话")])]), t._v(" "), n("a-col", {attrs: {span: 2}}, [n("span", {staticClass: "iconfont"}, [t._v("")]), n("br"), n("label", [t._v("多人视频")])]), t._v(" "), n("a-col", {attrs: {span: 2}}, [n("span", {staticClass: "iconfont"}, [t._v("")]), n("br"), n("label", [t._v("通讯录")])]), t._v(" "), n("a-col", {attrs: {span: 2}}, [n("span", {staticClass: "iconfont"}, [t._v("")]), n("br"), n("label", [t._v("发送图片")])]), t._v(" "), n("a-col", {attrs: {span: 2}}, [n("span", {staticClass: "iconfont"}, [t._v("")]), n("br"), n("label", [t._v("文字聊天")])]), t._v(" "), n("a-col", {attrs: {span: 7}})], 1)], 1)], 1)
            }, staticRenderFns: []
        };
        var U = n("VU/8")(D, I, !1, function (t) {
            n("uAuT")
        }, "data-v-806ee270", null).exports, L = n("8+8L");
        a.default.use(o.a), a.default.use(L.a);
        var F = new o.a({
            routes: [{path: "/", name: "Home", component: M}, {
                path: "/video",
                name: "Video",
                component: V
            }, {path: "/calling", name: "Calling", component: U}]
        }), R = (n("9n10"), n("TzC8"), n("zL8q")), O = n.n(R);
        n("tvR6");
        a.default.config.productionTip = !1, a.default.use(O.a), new a.default({
            router: F, render: function (t) {
                return t(i)
            }
        }).$mount("#app")
    }, TzC8: function (t, e) {
    }, VliT: function (t, e) {
    }, Xsl4: function (t, e) {
    }, ZUWK: function (t, e) {
    }, ZeJt: function (t, e) {
    }, ZgGj: function (t, e) {
    }, htXu: function (t, e) {
    }, "inB+": function (t, e) {
    }, jsUr: function (t, e) {
    }, n4dH: function (t, e) {
    }, nkBS: function (t, e) {
    }, pFwy: function (t, e) {
    }, rrf1: function (t, e) {
    }, tvR6: function (t, e) {
    }, uAuT: function (t, e) {
    }, uslO: function (t, e, n) {
        var a = {
            "./af": "3CJN",
            "./af.js": "3CJN",
            "./ar": "3MVc",
            "./ar-dz": "tkWw",
            "./ar-dz.js": "tkWw",
            "./ar-kw": "j8cJ",
            "./ar-kw.js": "j8cJ",
            "./ar-ly": "wPpW",
            "./ar-ly.js": "wPpW",
            "./ar-ma": "dURR",
            "./ar-ma.js": "dURR",
            "./ar-sa": "7OnE",
            "./ar-sa.js": "7OnE",
            "./ar-tn": "BEem",
            "./ar-tn.js": "BEem",
            "./ar.js": "3MVc",
            "./az": "eHwN",
            "./az.js": "eHwN",
            "./be": "3hfc",
            "./be.js": "3hfc",
            "./bg": "lOED",
            "./bg.js": "lOED",
            "./bm": "hng5",
            "./bm.js": "hng5",
            "./bn": "aM0x",
            "./bn.js": "aM0x",
            "./bo": "w2Hs",
            "./bo.js": "w2Hs",
            "./br": "OSsP",
            "./br.js": "OSsP",
            "./bs": "aqvp",
            "./bs.js": "aqvp",
            "./ca": "wIgY",
            "./ca.js": "wIgY",
            "./cs": "ssxj",
            "./cs.js": "ssxj",
            "./cv": "N3vo",
            "./cv.js": "N3vo",
            "./cy": "ZFGz",
            "./cy.js": "ZFGz",
            "./da": "YBA/",
            "./da.js": "YBA/",
            "./de": "DOkx",
            "./de-at": "8v14",
            "./de-at.js": "8v14",
            "./de-ch": "Frex",
            "./de-ch.js": "Frex",
            "./de.js": "DOkx",
            "./dv": "rIuo",
            "./dv.js": "rIuo",
            "./el": "CFqe",
            "./el.js": "CFqe",
            "./en-SG": "oYA3",
            "./en-SG.js": "oYA3",
            "./en-au": "Sjoy",
            "./en-au.js": "Sjoy",
            "./en-ca": "Tqun",
            "./en-ca.js": "Tqun",
            "./en-gb": "hPuz",
            "./en-gb.js": "hPuz",
            "./en-ie": "ALEw",
            "./en-ie.js": "ALEw",
            "./en-il": "QZk1",
            "./en-il.js": "QZk1",
            "./en-nz": "dyB6",
            "./en-nz.js": "dyB6",
            "./eo": "Nd3h",
            "./eo.js": "Nd3h",
            "./es": "LT9G",
            "./es-do": "7MHZ",
            "./es-do.js": "7MHZ",
            "./es-us": "INcR",
            "./es-us.js": "INcR",
            "./es.js": "LT9G",
            "./et": "XlWM",
            "./et.js": "XlWM",
            "./eu": "sqLM",
            "./eu.js": "sqLM",
            "./fa": "2pmY",
            "./fa.js": "2pmY",
            "./fi": "nS2h",
            "./fi.js": "nS2h",
            "./fo": "OVPi",
            "./fo.js": "OVPi",
            "./fr": "tzHd",
            "./fr-ca": "bXQP",
            "./fr-ca.js": "bXQP",
            "./fr-ch": "VK9h",
            "./fr-ch.js": "VK9h",
            "./fr.js": "tzHd",
            "./fy": "g7KF",
            "./fy.js": "g7KF",
            "./ga": "U5Iz",
            "./ga.js": "U5Iz",
            "./gd": "nLOz",
            "./gd.js": "nLOz",
            "./gl": "FuaP",
            "./gl.js": "FuaP",
            "./gom-latn": "+27R",
            "./gom-latn.js": "+27R",
            "./gu": "rtsW",
            "./gu.js": "rtsW",
            "./he": "Nzt2",
            "./he.js": "Nzt2",
            "./hi": "ETHv",
            "./hi.js": "ETHv",
            "./hr": "V4qH",
            "./hr.js": "V4qH",
            "./hu": "xne+",
            "./hu.js": "xne+",
            "./hy-am": "GrS7",
            "./hy-am.js": "GrS7",
            "./id": "yRTJ",
            "./id.js": "yRTJ",
            "./is": "upln",
            "./is.js": "upln",
            "./it": "FKXc",
            "./it-ch": "/E8D",
            "./it-ch.js": "/E8D",
            "./it.js": "FKXc",
            "./ja": "ORgI",
            "./ja.js": "ORgI",
            "./jv": "JwiF",
            "./jv.js": "JwiF",
            "./ka": "RnJI",
            "./ka.js": "RnJI",
            "./kk": "j+vx",
            "./kk.js": "j+vx",
            "./km": "5j66",
            "./km.js": "5j66",
            "./kn": "gEQe",
            "./kn.js": "gEQe",
            "./ko": "eBB/",
            "./ko.js": "eBB/",
            "./ku": "kI9l",
            "./ku.js": "kI9l",
            "./ky": "6cf8",
            "./ky.js": "6cf8",
            "./lb": "z3hR",
            "./lb.js": "z3hR",
            "./lo": "nE8X",
            "./lo.js": "nE8X",
            "./lt": "/6P1",
            "./lt.js": "/6P1",
            "./lv": "jxEH",
            "./lv.js": "jxEH",
            "./me": "svD2",
            "./me.js": "svD2",
            "./mi": "gEU3",
            "./mi.js": "gEU3",
            "./mk": "Ab7C",
            "./mk.js": "Ab7C",
            "./ml": "oo1B",
            "./ml.js": "oo1B",
            "./mn": "CqHt",
            "./mn.js": "CqHt",
            "./mr": "5vPg",
            "./mr.js": "5vPg",
            "./ms": "ooba",
            "./ms-my": "G++c",
            "./ms-my.js": "G++c",
            "./ms.js": "ooba",
            "./mt": "oCzW",
            "./mt.js": "oCzW",
            "./my": "F+2e",
            "./my.js": "F+2e",
            "./nb": "FlzV",
            "./nb.js": "FlzV",
            "./ne": "/mhn",
            "./ne.js": "/mhn",
            "./nl": "3K28",
            "./nl-be": "Bp2f",
            "./nl-be.js": "Bp2f",
            "./nl.js": "3K28",
            "./nn": "C7av",
            "./nn.js": "C7av",
            "./pa-in": "pfs9",
            "./pa-in.js": "pfs9",
            "./pl": "7LV+",
            "./pl.js": "7LV+",
            "./pt": "ZoSI",
            "./pt-br": "AoDM",
            "./pt-br.js": "AoDM",
            "./pt.js": "ZoSI",
            "./ro": "wT5f",
            "./ro.js": "wT5f",
            "./ru": "ulq9",
            "./ru.js": "ulq9",
            "./sd": "fW1y",
            "./sd.js": "fW1y",
            "./se": "5Omq",
            "./se.js": "5Omq",
            "./si": "Lgqo",
            "./si.js": "Lgqo",
            "./sk": "OUMt",
            "./sk.js": "OUMt",
            "./sl": "2s1U",
            "./sl.js": "2s1U",
            "./sq": "V0td",
            "./sq.js": "V0td",
            "./sr": "f4W3",
            "./sr-cyrl": "c1x4",
            "./sr-cyrl.js": "c1x4",
            "./sr.js": "f4W3",
            "./ss": "7Q8x",
            "./ss.js": "7Q8x",
            "./sv": "Fpqq",
            "./sv.js": "Fpqq",
            "./sw": "DSXN",
            "./sw.js": "DSXN",
            "./ta": "+7/x",
            "./ta.js": "+7/x",
            "./te": "Nlnz",
            "./te.js": "Nlnz",
            "./tet": "gUgh",
            "./tet.js": "gUgh",
            "./tg": "5SNd",
            "./tg.js": "5SNd",
            "./th": "XzD+",
            "./th.js": "XzD+",
            "./tl-ph": "3LKG",
            "./tl-ph.js": "3LKG",
            "./tlh": "m7yE",
            "./tlh.js": "m7yE",
            "./tr": "k+5o",
            "./tr.js": "k+5o",
            "./tzl": "iNtv",
            "./tzl.js": "iNtv",
            "./tzm": "FRPF",
            "./tzm-latn": "krPU",
            "./tzm-latn.js": "krPU",
            "./tzm.js": "FRPF",
            "./ug-cn": "To0v",
            "./ug-cn.js": "To0v",
            "./uk": "ntHu",
            "./uk.js": "ntHu",
            "./ur": "uSe8",
            "./ur.js": "uSe8",
            "./uz": "XU1s",
            "./uz-latn": "/bsm",
            "./uz-latn.js": "/bsm",
            "./uz.js": "XU1s",
            "./vi": "0X8Q",
            "./vi.js": "0X8Q",
            "./x-pseudo": "e/KL",
            "./x-pseudo.js": "e/KL",
            "./yo": "YXlc",
            "./yo.js": "YXlc",
            "./zh-cn": "Vz2w",
            "./zh-cn.js": "Vz2w",
            "./zh-hk": "ZUyn",
            "./zh-hk.js": "ZUyn",
            "./zh-tw": "BbgG",
            "./zh-tw.js": "BbgG"
        };

        function s(t) {
            return n(i(t))
        }

        function i(t) {
            var e = a[t];
            if (!(e + 1)) throw new Error("Cannot find module '" + t + "'.");
            return e
        }

        s.keys = function () {
            return Object.keys(a)
        }, s.resolve = i, t.exports = s, s.id = "uslO"
    }, vnFo: function (t, e) {
    }
}, ["NHnr"]);
// //# sourceMappingURL=app.ff9b1ca03b0a32839454.js.map