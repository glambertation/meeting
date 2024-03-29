/*! Socket.IO.js build:0.9.0, development. Copyright(c) 2011 LearnBoost <dev@learnboost.com> MIT Licensed */
(function (n, t) {
    var i = n;
    i.version = "0.9.0", i.protocol = 1, i.transports = [], i.j = [], i.sockets = {}, i.connect = function (n, r) {
        var u = i.util.parseUri(n), o, f, e;
        return t && t.location && (u.protocol = u.protocol || t.location.protocol.slice(0, -1), u.host = u.host || (t.document ? t.document.domain : t.location.hostname), u.port = u.port || t.location.port), o = i.util.uniqueUri(u), e = {
            host: u.host,
            secure: "https" == u.protocol,
            port: u.port || ("https" == u.protocol ? 443 : 80),
            query: u.query || ""
        }, i.util.merge(e, r), (e["force new connection"] || !i.sockets[o]) && (f = new i.Socket(e)), !e["force new connection"] && f && (i.sockets[o] = f), f = f || i.sockets[o], f.of(u.path.length > 1 ? u.path : "")
    
    }
})("object" == typeof module ? module.exports : this.io = {}, this), (function (n, t) {
    var i = n.util = {},
        f = /^(?:(?![^:@]+:[^:@\/]*@)([^:\/?#.]+):)?(?:\/\/)?((?:(([^:@]*)(?::([^:@]*))?)?@)?([^:\/?#]*)(?::(\d*))?)(((\/(?:[^?#](?![^?#\/]*\.[^?#\/.]+(?:[?#]|$)))*\/?)?([^?#\/]*))(?:\?([^#]*))?(?:#(.*))?)/,
        u = ["source", "protocol", "authority", "userInfo", "user", "password", "host", "port", "relative", "path", "directory", "file", "query", "anchor"],
        r;
    i.parseUri = function (n) {
        var r = f.exec(n || ""), i = {}, t = 14;
        while (t--) i[u[t]] = r[t] || "";
        return i
    }, i.uniqueUri = function (n) {
        var u = n.protocol, i = n.host, r = n.port;
        return "document" in t ? (i = i || document.domain, r = r || (u == "https" && document.location.protocol !== "https:" ? 443 : document.location.port)) : (i = i || "localhost", r || u != "https" || (r = 443)), (u || "http") + "://" + i + ":" + (r || 80)
    }, i.query = function (n, t) {
        var u = i.chunkQuery(n || ""), f = [], r;
        i.merge(u, i.chunkQuery(t || ""));
        for (r in u) u.hasOwnProperty(r) && f.push(r + "=" + u[r]);
        return f.length ? "?" + f.join("&") : ""
    }, i.chunkQuery = function (n) {
        for (var u = {}, r = n.split("&"), i = 0, f = r.length, t; i < f; ++i) t = r[i].split("="), t[0] && (u[t[0]] = t[1]);
        return u
    }, r = !1, i.load = function (n) {
        if ("document" in t && document.readyState === "complete" || r) return n();
        i.on(t, "load", n, !1)
    }, i.on = function (n, t, i, r) {
        n.attachEvent ? n.attachEvent("on" + t, i) : n.addEventListener && n.addEventListener(t, i, r)
    }, i.request = function (n) {
        if (n && "undefined" != typeof XDomainRequest) return new XDomainRequest;
        if ("undefined" != typeof XMLHttpRequest && (!n || i.ua.hasCORS)) return new XMLHttpRequest;
        if (!n) try {
            return new ActiveXObject("Microsoft.XMLHTTP")
        } catch (t) {
        }
        return null
    }, "undefined" != typeof window && i.load(function () {
        r = !0
    }), i.defer = function (n) {
        if (!i.ua.webkit || "undefined" != typeof importScripts) return n();
        i.load(function () {
            setTimeout(n, 100)
        })
    }, i.merge = function (n, t, r, u) {
        var e = u || [], o = typeof r == "undefined" ? 2 : r, f;
        for (f in t) t.hasOwnProperty(f) && i.indexOf(e, f) < 0 && (typeof n[f] == "object" && o ? i.merge(n[f], t[f], o - 1, e) : (n[f] = t[f], e.push(t[f])));
        return n
    }, i.mixin = function (n, t) {
        i.merge(n.prototype, t.prototype)
    }, i.inherit = function (n, t) {
        function i() {
        }

        i.prototype = t.prototype, n.prototype = new i
    }, i.isArray = Array.isArray || function (n) {
        return Object.prototype.toString.call(n) === "[object Array]"
    }, i.intersect = function (n, t) {
        for (var f = [], o = n.length > t.length ? n : t, u = n.length > t.length ? t : n, r = 0, e = u.length; r < e; r++) ~i.indexOf(o, u[r]) && f.push(u[r]);
        return f
    }, i.indexOf = function (n, t, i) {
        for (var r = n.length, i = i < 0 ? i + r < 0 ? 0 : i + r : i || 0; i < r && n[i] !== t; i++) ;
        return r <= i ? -1 : i
    }, i.toArray = function (n) {
        for (var r = [], t = 0, i = n.length; t < i; t++) r.push(n[t]);
        return r
    }, i.ua = {}, i.ua.hasCORS = "undefined" != typeof XMLHttpRequest && function () {
        try {
            var n = new XMLHttpRequest
        } catch (t) {
            return !1
        }
        return n.withCredentials != undefined
    }(), i.ua.webkit = "undefined" != typeof navigator && /webkit/i.test(navigator.userAgent)
})("undefined" != typeof io ? io : module.exports, this), (function (n, t) {
    function i() {
    }

    n.EventEmitter = i, i.prototype.on = function (n, i) {
        return this.$events || (this.$events = {}), this.$events[n] ? t.util.isArray(this.$events[n]) ? this.$events[n].push(i) : this.$events[n] = [this.$events[n], i] : this.$events[n] = i, this
    }, i.prototype.addListener = i.prototype.on, i.prototype.once = function (n, t) {
        function i() {
            r.removeListener(n, i), t.apply(this, arguments)
        }

        var r = this;
        i.listener = t;
        this.on(n, i);
        return this
    }, i.prototype.removeListener = function (n, i) {
        var r, f, u, e;
        if (this.$events && this.$events[n]) {
            r = this.$events[n];
            if (t.util.isArray(r)) {
                for (f = -1, u = 0, e = r.length; u < e; u++) if (r[u] === i || r[u].listener && r[u].listener === i) {
                    f = u;
                    break
                }
                if (f < 0) return this;
                r.splice(f, 1), r.length || delete this.$events[n]
            } else (r === i || r.listener && r.listener === i) && delete this.$events[n]
        }
        return this
    }, i.prototype.removeAllListeners = function (n) {
        return this.$events && this.$events[n] && (this.$events[n] = null), this
    }, i.prototype.listeners = function (n) {
        return this.$events || (this.$events = {}), this.$events[n] || (this.$events[n] = []), t.util.isArray(this.$events[n]) || (this.$events[n] = [this.$events[n]]), this.$events[n]
    }, i.prototype.emit = function (n) {
        var i, f, u, r, e;
        if (!this.$events) return !1;
        i = this.$events[n];
        if (!i) return !1;
        f = Array.prototype.slice.call(arguments, 1);
        if ("function" == typeof i) i.apply(this, f); else if (t.util.isArray(i)) for (u = i.slice(), r = 0, e = u.length; r < e; r++) u[r].apply(this, f); else return !1;
        return !0
    }
})("undefined" != typeof io ? io : module.exports, "undefined" != typeof io ? io : module.parent.exports), (function (n, t) {
    "use strict";

    function u(n) {
        return n < 10 ? "0" + n : n
    }

    function a(n) {
        return isFinite(n.valueOf()) ? n.getUTCFullYear() + "-" + u(n.getUTCMonth() + 1) + "-" + u(n.getUTCDate()) + "T" + u(n.getUTCHours()) + ":" + u(n.getUTCMinutes()) + ":" + u(n.getUTCSeconds()) + "Z" : null
    }

    function s(n) {
        return h.lastIndex = 0, h.test(n) ? '"' + n.replace(h, function (n) {
            var t = l[n];
            return typeof t == "string" ? t : "\\u" + ("0000" + n.charCodeAt(0).toString(16)).slice(-4)
        }) + '"' : '"' + n + '"'
    }

    function e(n, t) {
        var h, l, c, v, y = i, o, u = t[n];
        u instanceof Date && (u = a(n)), typeof r == "function" && (u = r.call(t, n, u));
        switch (typeof u) {
            case"string":
                return s(u);
            case"number":
                return isFinite(u) ? String(u) : "null";
            case"boolean":
            case"null":
                return String(u);
            case"object":
                if (!u) return "null";
                i += f, o = [];
                if (Object.prototype.toString.apply(u) === "[object Array]") {
                    for (v = u.length, h = 0; h < v; h += 1) o[h] = e(h, u) || "null";
                    return c = o.length === 0 ? "[]" : i ? "[\n" + i + o.join(",\n" + i) + "\n" + y + "]" : "[" + o.join(",") + "]", i = y, c
                }
                if (r && typeof r == "object") for (v = r.length, h = 0; h < v; h += 1) typeof r[h] == "string" && (l = r[h], c = e(l, u), c && o.push(s(l) + (i ? ": " : ":") + c)); else for (l in u) Object.prototype.hasOwnProperty.call(u, l) && (c = e(l, u), c && o.push(s(l) + (i ? ": " : ":") + c));
                return c = o.length === 0 ? "{}" : i ? "{\n" + i + o.join(",\n" + i) + "\n" + y + "}" : "{" + o.join(",") + "}", i = y, c
        }
    }

    var o;
    if (t && t.parse) return n.JSON = {parse: t.parse, stringify: t.stringify};
    o = n.JSON = {};
    var c = /[\u0000\u00ad\u0600-\u0604\u070f\u17b4\u17b5\u200c-\u200f\u2028-\u202f\u2060-\u206f\ufeff\ufff0-\uffff]/g,
        h = /[\\\"\x00-\x1f\x7f-\x9f\u00ad\u0600-\u0604\u070f\u17b4\u17b5\u200c-\u200f\u2028-\u202f\u2060-\u206f\ufeff\ufff0-\uffff]/g,
        i, f, l = {"\b": "\\b", "\t": "\\t", "\n": "\\n", "\f": "\\f", "\r": "\\r", '"': '\\"', "\\": "\\\\"}, r;
    o.stringify = function (n, t, u) {
        var o;
        i = "", f = "";
        if (typeof u == "number") for (o = 0; o < u; o += 1) f += " "; else typeof u == "string" && (f = u);
        r = t;
        if (t && typeof t != "function" && (typeof t != "object" || typeof t.length != "number")) throw new Error("JSON.stringify");
        return e("", {"": n})
    }, o.parse = function (n, t) {
        function r(n, i) {
            var f, e, u = n[i];
            if (u && typeof u == "object") for (f in u) Object.prototype.hasOwnProperty.call(u, f) && (e = r(u, f), e !== undefined ? u[f] = e : delete u[f]);
            return t.call(n, i, u)
        }

        var i;
        n = String(n), c.lastIndex = 0, c.test(n) && (n = n.replace(c, function (n) {
            return "\\u" + ("0000" + n.charCodeAt(0).toString(16)).slice(-4)
        }));
        if (/^[\],:{}\s]*$/.test(n.replace(/\\(?:["\\\/bfnrt]|u[0-9a-fA-F]{4})/g, "@").replace(/"[^"\\\n\r]*"|true|false|null|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?/g, "]").replace(/(?:^|:|,)(?:\s*\[)+/g, ""))) return i = eval("(" + n + ")"), typeof t == "function" ? r({"": i}, "") : i;
        throw new SyntaxError("JSON.parse");
    }
})("undefined" != typeof io ? io : module.exports, typeof JSON != "undefined" ? JSON : undefined), (function (n, t) {
    var i = n.parser = {},
        e = i.packets = ["disconnect", "connect", "heartbeat", "message", "json", "event", "ack", "error", "noop"],
        o = i.reasons = ["transport not supported", "client not handshaken", "unauthorized"],
        s = i.advice = ["reconnect"], r = t.JSON, u = t.util.indexOf, f;
    i.encodePacket = function (n) {
        var l = u(e, n.type), y = n.id || "", v = n.endpoint || "", a = n.ack, t = null, h, i, f, c;
        switch (n.type) {
            case"error":
                h = n.reason ? u(o, n.reason) : "", i = n.advice ? u(s, n.advice) : "", (h !== "" || i !== "") && (t = h + (i !== "" ? "+" + i : ""));
                break;
            case"message":
                n.data !== "" && (t = n.data);
                break;
            case"event":
                f = {name: n.name}, n.args && n.args.length && (f.args = n.args), t = r.stringify(f);
                break;
            case"json":
                t = r.stringify(n.data);
                break;
            case"connect":
                n.qs && (t = n.qs);
                break;
            case"ack":
                t = n.ackId + (n.args && n.args.length ? "+" + r.stringify(n.args) : "")
        }
        return c = [l, y + (a == "data" ? "+" : ""), v], t !== null && t !== undefined && c.push(t), c.join(":")
    }, i.encodePayload = function (n) {
        var u = "", t, r, i;
        if (n.length == 1) return n[0];
        for (t = 0, r = n.length; t < r; t++) i = n[t], u += "\ufffd" + i.length + "\ufffd" + n[t];
        return u
    }, f = /([^:]+):([0-9]+)?(\+)?:([^:]+)?:?([\s\S]*)?/, i.decodePacket = function (n) {
        var i = n.match(f), u;
        if (!i) return {};
        var h = i[2] || "", n = i[5] || "", t = {type: e[i[1]], endpoint: i[4] || ""};
        h && (t.id = h, t.ack = i[3] ? "data" : !0);
        switch (t.type) {
            case"error":
                i = n.split("+"), t.reason = o[i[0]] || "", t.advice = s[i[1]] || "";
                break;
            case"message":
                t.data = n || "";
                break;
            case"event":
                try {
                    u = r.parse(n), t.name = u.name, t.args = u.args
                } catch (c) {
                }
                t.args = t.args || [];
                break;
            case"json":
                try {
                    t.data = r.parse(n)
                } catch (c) {
                }
                break;
            case"connect":
                t.qs = n || "";
                break;
            case"ack":
                i = n.match(/^([0-9]+)(\+)?(.*)/);
                if (i) {
                    t.ackId = i[1], t.args = [];
                    if (i[3]) try {
                        t.args = i[3] ? r.parse(i[3]) : []
                    } catch (c) {
                    }
                }
        }
        return t
    }, i.decodePayload = function (n) {
        var u, t, r;
        if (n.charAt(0) == "\ufffd") {
            for (u = [], t = 1, r = ""; t < n.length; t++) n.charAt(t) == "\ufffd" ? (u.push(i.decodePacket(n.substr(t + 1).substr(0, r))), t += Number(r) + 1, r = "") : r += n.charAt(t);
            return u
        }
        return [i.decodePacket(n)]
    }
})("undefined" != typeof io ? io : module.exports, "undefined" != typeof io ? io : module.parent.exports), (function (n, t) {
    function i(n, t) {
        this.socket = n, this.sessid = t
    }

    n.Transport = i, t.util.mixin(i, t.EventEmitter), i.prototype.onData = function (n) {
        var i, r, u;
        this.clearCloseTimeout(), (this.socket.connected || this.socket.connecting || this.socket.reconnecting) && this.setCloseTimeout();
        if (n !== "") {
            i = t.parser.decodePayload(n);
            if (i && i.length) for (r = 0, u = i.length; r < u; r++) this.onPacket(i[r])
        }
        return this
    }, i.prototype.onPacket = function (n) {
        if (n.type == "heartbeat") return this.onHeartbeat();
        n.type == "connect" && n.endpoint == "" && this.onConnect();
        this.socket.onPacket(n);
        return this
    }, i.prototype.setCloseTimeout = function () {
        if (!this.closeTimeout) {
            var n = this;
            this.closeTimeout = setTimeout(function () {
                n.onDisconnect()
            }, this.socket.closeTimeout)
        }
    }, i.prototype.onDisconnect = function () {
        return this.close && this.open && this.close(), this.clearTimeouts(), this.socket.onDisconnect(), this
    }, i.prototype.onConnect = function () {
        return this.socket.onConnect(), this
    }, i.prototype.clearCloseTimeout = function () {
        this.closeTimeout && (clearTimeout(this.closeTimeout), this.closeTimeout = null)
    }, i.prototype.clearTimeouts = function () {
        this.clearCloseTimeout(), this.reopenTimeout && clearTimeout(this.reopenTimeout)
    }, i.prototype.packet = function (n) {
        this.send(t.parser.encodePacket(n))
    }, i.prototype.onHeartbeat = function () {
        this.packet({type: "heartbeat"})
    }, i.prototype.onOpen = function () {
        this.open = !0, this.clearCloseTimeout(), this.socket.onOpen()
    }, i.prototype.onClose = function () {
        var n = this;
        this.open = !1, this.socket.onClose(), this.onDisconnect()
    }, i.prototype.prepareUrl = function () {
        var n = this.socket.options;
        return this.scheme() + "://" + n.host + ":" + n.port + "/" + n.resource + "/" + t.protocol + "/" + this.name + "/" + this.sessid
    }, i.prototype.ready = function (n, t) {
        t.call(this)
    }
})("undefined" != typeof io ? io : module.exports, "undefined" != typeof io ? io : module.parent.exports), (function (n, t, i) {
    function r(n) {
        this.options = {
            port: 80,
            secure: !1,
            document: "document" in i ? document : !1,
            resource: "socket.io",
            transports: t.transports,
            "connect timeout": 1e4,
            "try multiple transports": !0,
            reconnect: !0,
            "reconnection delay": 500,
            "reconnection limit": Infinity,
            "reopen delay": 3e3,
            "max reconnection attempts": 10,
            "sync disconnect on unload": !0,
            "auto connect": !0,
            "flash policy port": 10843
        }, t.util.merge(this.options, n), this.connected = !1, this.open = !1, this.connecting = !1, this.reconnecting = !1, this.namespaces = {}, this.buffer = [], this.doBuffer = !1;
        if (this.options["sync disconnect on unload"] && (!this.isXDomain() || t.util.ua.hasCORS)) {
            var r = this;
            t.util.on(i, "beforeunload", function () {
                r.disconnectSync()
            }, !1)
        }
        this.options["auto connect"] && this.connect()
    }

    function u() {
    }

    n.Socket = r, t.util.mixin(r, t.EventEmitter), r.prototype.of = function (n) {
        return this.namespaces[n] || (this.namespaces[n] = new t.SocketNamespace(this, n), n !== "" && this.namespaces[n].packet({type: "connect"})), this.namespaces[n]
    }, r.prototype.publish = function () {
        var t, n;
        this.emit.apply(this, arguments);
        for (n in this.namespaces) this.namespaces.hasOwnProperty(n) && (t = this.of(n), t.$emit.apply(t, arguments))
    }, r.prototype.handshake = function (n) {
        function s(t) {
            if (t instanceof Error) o.onError(t.message); else n.apply(null, t.split(":"))
        }

        var o = this, f = this.options,
            h = ["http" + (f.secure ? "s" : "") + ":/", f.host + ":" + f.port, f.resource, t.protocol, t.util.query(this.options.query, "t=" + +new Date)].join("/"),
            e, r, i;
        this.isXDomain() && !t.util.ua.hasCORS ? (e = document.getElementsByTagName("script")[0], r = document.createElement("script"), r.src = h + "&jsonp=" + t.j.length, e.parentNode.insertBefore(r, e), t.j.push(function (n) {
            s(n), r.parentNode.removeChild(r)
        })) : (i = t.util.request(), i.open("GET", h, !0), i.withCredentials = !0, i.onreadystatechange = function () {
            i.readyState == 4 && (i.onreadystatechange = u, i.status == 200 ? s(i.responseText) : !o.reconnecting && o.onError(i.responseText))
        }, i.send(null))
    }, r.prototype.getTransport = function (n) {
        for (var u = n || this.transports, f, i, r = 0; i = u[r]; r++) if (t.Transport[i] && t.Transport[i].check(this) && (!this.isXDomain() || t.Transport[i].xdomainCheck())) return new t.Transport[i](this, this.sessionid);
        return null
    }, r.prototype.connect = function (n) {
        if (this.connecting) return this;
        var i = this;
        return this.handshake(function (r, u, f, e) {
            function o(n) {
                i.transport && i.transport.clearTimeouts(), i.transport = i.getTransport(n);
                if (!i.transport) return i.publish("connect_failed");
                i.transport.ready(i, function () {
                    i.connecting = !0, i.publish("connecting", i.transport.name), i.transport.open(), i.options["connect timeout"] && (i.connectTimeoutTimer = setTimeout(function () {
                        if (!i.connected) {
                            i.connecting = !1;
                            if (i.options["try multiple transports"]) {
                                i.remainingTransports || (i.remainingTransports = i.transports.slice(0));
                                var n = i.remainingTransports;
                                while (n.length > 0 && n.splice(0, 1)[0] != i.transport.name) ;
                                n.length ? o(n) : i.publish("connect_failed")
                            }
                        }
                    }, i.options["connect timeout"]))
                })
            }

            i.sessionid = r, i.closeTimeout = f * 1e3, i.heartbeatTimeout = u * 1e3, i.transports = t.util.intersect(e.split(","), i.options.transports), o();
            i.once("connect", function () {
                clearTimeout(i.connectTimeoutTimer), n && typeof n == "function" && n()
            })
        }), this
    }, r.prototype.packet = function (n) {
        return this.connected && !this.doBuffer ? this.transport.packet(n) : this.buffer.push(n), this
    }, r.prototype.setBuffer = function (n) {
        this.doBuffer = n, !n && this.connected && this.buffer.length && (this.transport.payload(this.buffer), this.buffer = [])
    }, r.prototype.disconnect = function () {
        if (this.connected) {
            this.open && this.of("").packet({type: "disconnect"});
            this.onDisconnect("booted")
        }
        return this
    }, r.prototype.disconnectSync = function () {
        var i = t.util.request(), n = this.resource + "/" + t.protocol + "/" + this.sessionid;
        i.open("GET", n, !0);
        this.onDisconnect("booted")
    }, r.prototype.isXDomain = function () {
        var n = i.location.port || ("https:" == i.location.protocol ? 443 : 80);
        return this.options.host !== i.location.hostname || this.options.port != n
    }, r.prototype.onConnect = function () {
        this.connected || (this.connected = !0, this.connecting = !1, this.doBuffer || this.setBuffer(!1), this.emit("connect"))
    }, r.prototype.onOpen = function () {
        this.open = !0
    }, r.prototype.onClose = function () {
        this.open = !1
    }, r.prototype.onPacket = function (n) {
        this.of(n.endpoint).onPacket(n)
    }, r.prototype.onError = function (n) {
        n && n.advice && this.options.reconnect && n.advice === "reconnect" && this.connected && (this.disconnect(), this.reconnect()), this.publish("error", n && n.reason ? n.reason : n)
    }, r.prototype.onDisconnect = function (n) {
        var t = this.connected;
        this.connected = !1, this.connecting = !1, this.open = !1, t && (this.transport.close(), this.transport.clearTimeouts(), this.publish("disconnect", n), "booted" != n && this.options.reconnect && !this.reconnecting && this.reconnect())
    }, r.prototype.reconnect = function () {
        function i() {
            if (n.connected) {
                for (var i in n.namespaces) n.namespaces.hasOwnProperty(i) && "" !== i && n.namespaces[i].packet({type: "connect"});
                n.publish("reconnect", n.transport.name, n.reconnectionAttempts)
            }
            clearTimeout(n.reconnectionTimer), n.removeListener("connect_failed", t), n.removeListener("connect", t), n.reconnecting = !1, delete n.reconnectionAttempts, delete n.reconnectionDelay, delete n.reconnectionTimer, delete n.redoTransports, n.options["try multiple transports"] = r
        }

        function t() {
            if (!n.reconnecting) return;
            if (n.connected) return i();
            if (n.connecting && n.reconnecting) return n.reconnectionTimer = setTimeout(t, 1e3);
            if (n.reconnectionAttempts++ < u) n.reconnectionDelay < f && (n.reconnectionDelay *= 2), n.connect(), n.publish("reconnecting", n.reconnectionDelay, n.reconnectionAttempts), n.reconnectionTimer = setTimeout(t, n.reconnectionDelay); else if (n.redoTransports) n.publish("reconnect_failed"), i(); else {
                n.on("connect_failed", t);
                n.options["try multiple transports"] = !0, n.transport = n.getTransport(), n.redoTransports = !0, n.connect()
            }
        }

        this.reconnecting = !0, this.reconnectionAttempts = 0, this.reconnectionDelay = this.options["reconnection delay"];
        var n = this, u = this.options["max reconnection attempts"], r = this.options["try multiple transports"],
            f = this.options["reconnection limit"];
        this.options["try multiple transports"] = !1, this.reconnectionTimer = setTimeout(t, this.reconnectionDelay);
        this.on("connect", t)
    }
})("undefined" != typeof io ? io : module.exports, "undefined" != typeof io ? io : module.parent.exports, this), (function (n, t) {
    function i(n, t) {
        this.socket = n, this.name = t || "", this.flags = {}, this.json = new r(this, "json"), this.ackPackets = 0, this.acks = {}
    }

    function r(n, t) {
        this.namespace = n, this.name = t
    }

    n.SocketNamespace = i, t.util.mixin(i, t.EventEmitter), i.prototype.$emit = t.EventEmitter.prototype.emit, i.prototype.of = function () {
        return this.socket.of.apply(this.socket, arguments)
    }, i.prototype.packet = function (n) {
        return n.endpoint = this.name, this.socket.packet(n), this.flags = {}, this
    }, i.prototype.send = function (n, t) {
        var i = {type: this.flags.json ? "json" : "message", data: n};
        return "function" == typeof t && (i.id = ++this.ackPackets, i.ack = !0, this.acks[i.id] = t), this.packet(i)
    }, i.prototype.emit = function (n) {
        var t = Array.prototype.slice.call(arguments, 1), r = t[t.length - 1], i = {type: "event", name: n};
        return "function" == typeof r && (i.id = ++this.ackPackets, i.ack = "data", this.acks[i.id] = r, t = t.slice(0, t.length - 1)), i.args = t, this.packet(i)
    }, i.prototype.disconnect = function () {
        return this.name === "" ? this.socket.disconnect() : (this.packet({type: "disconnect"}), this.$emit("disconnect")), this
    }, i.prototype.onPacket = function (n) {
        function r() {
            u.packet({type: "ack", args: t.util.toArray(arguments), ackId: n.id})
        }

        var u = this, i;
        switch (n.type) {
            case"connect":
                this.$emit("connect");
                break;
            case"disconnect":
                if (this.name === "") this.socket.onDisconnect(n.reason || "booted"); else this.$emit("disconnect", n.reason);
                break;
            case"message":
            case"json":
                i = ["message", n.data], n.ack == "data" ? i.push(r) : n.ack && this.packet({
                    type: "ack",
                    ackId: n.id
                }), this.$emit.apply(this, i);
                break;
            case"event":
                i = [n.name].concat(n.args), n.ack == "data" && i.push(r), this.$emit.apply(this, i);
                break;
            case"ack":
                this.acks[n.ackId] && (this.acks[n.ackId].apply(this, n.args), delete this.acks[n.ackId]);
                break;
            case"error":
                if (n.advice) this.socket.onError(n); else n.reason == "unauthorized" ? this.$emit("connect_failed", n.reason) : this.$emit("error", n.reason)
        }
    }, r.prototype.send = function () {
        this.namespace.flags[this.name] = !0, this.namespace.send.apply(this.namespace, arguments)
    }, r.prototype.emit = function () {
        this.namespace.flags[this.name] = !0, this.namespace.emit.apply(this.namespace, arguments)
    }
})("undefined" != typeof io ? io : module.exports, "undefined" != typeof io ? io : module.parent.exports), (function (n, t, i) {
    function r() {
        t.Transport.apply(this, arguments)
    }

    n.websocket = r, t.util.inherit(r, t.Transport), r.prototype.name = "websocket", r.prototype.open = function () {
        var u = t.util.query(this.socket.options.query), n = this, r;
        return r || (r = i.MozWebSocket || i.WebSocket), this.websocket = new r(this.prepareUrl() + u), this.websocket.onopen = function () {
            n.onOpen(), n.socket.setBuffer(!1)
        }, this.websocket.onmessage = function (t) {
            n.onData(t.data)
        }, this.websocket.onclose = function () {
            n.onClose(), n.socket.setBuffer(!0)
        }, this.websocket.onerror = function (t) {
            n.onError(t)
        }, this
    }, r.prototype.send = function (n) {
        return this.websocket.send(n), this
    }, r.prototype.payload = function (n) {
        for (var t = 0, i = n.length; t < i; t++) this.packet(n[t]);
        return this
    }, r.prototype.close = function () {
        return this.websocket.close(), this
    }, r.prototype.onError = function (n) {
        this.socket.onError(n)
    }, r.prototype.scheme = function () {
        return this.socket.options.secure ? "wss" : "ws"
    }, r.check = function () {
        return "WebSocket" in i && !("__addTask" in WebSocket) || "MozWebSocket" in i
    }, r.xdomainCheck = function () {
        return !0
    }, t.transports.push("websocket")
})("undefined" != typeof io ? io.Transport : module.exports, "undefined" != typeof io ? io : module.parent.exports, this), (function (n, t) {
    function i() {
        t.Transport.websocket.apply(this, arguments)
    }

    n.flashsocket = i, t.util.inherit(i, t.Transport.websocket), i.prototype.name = "flashsocket", i.prototype.open = function () {
        var i = this, n = arguments;
        return WebSocket.__addTask(function () {
            t.Transport.websocket.prototype.open.apply(i, n)
        }), this
    }, i.prototype.send = function () {
        var i = this, n = arguments;
        return WebSocket.__addTask(function () {
            t.Transport.websocket.prototype.send.apply(i, n)
        }), this
    }, i.prototype.close = function () {
        return WebSocket.__tasks.length = 0, t.Transport.websocket.prototype.close.call(this), this
    }, i.prototype.ready = function (n, r) {
        function u() {
            var t = n.options, u = t["flash policy port"],
                e = ["http" + (t.secure ? "s" : "") + ":/", t.host + ":" + t.port, t.resource, "static/flashsocket", "WebSocketMain" + (n.isXDomain() ? "Insecure" : "") + ".swf"];
            i.loaded || (typeof WEB_SOCKET_SWF_LOCATION == "undefined" && (WEB_SOCKET_SWF_LOCATION = e.join("/")), u !== 843 && WebSocket.loadFlashPolicyFile("xmlsocket://" + t.host + ":" + u), WebSocket.__initialize(), i.loaded = !0), r.call(f)
        }

        var f = this;
        if (document.body) return u();
        t.util.load(u)
    }, i.check = function () {
        return typeof WebSocket == "undefined" || !("__initialize" in WebSocket) || !swfobject ? !1 : swfobject.getFlashPlayerVersion().major >= 10
    }, i.xdomainCheck = function () {
        return !0
    }, typeof window != "undefined" && (WEB_SOCKET_DISABLE_AUTO_INITIALIZATION = !0), t.transports.push("flashsocket")
})("undefined" != typeof io ? io.Transport : module.exports, "undefined" != typeof io ? io : module.parent.exports);
if ("undefined" != typeof window) var swfobject = function () {
    function v() {
        var i, r, n;
        if (c) return;
        try {
            i = t.getElementsByTagName("body")[0].appendChild(s("span")), i.parentNode.removeChild(i)
        } catch (u) {
            return
        }
        for (c = !0, r = b.length, n = 0; n < r; n++) b[n]()
    }

    function kt(n) {
        c ? n() : b[b.length] = n
    }

    function pt(n) {
        if (typeof r.addEventListener != i) r.addEventListener("load", n, !1); else if (typeof t.addEventListener != i) t.addEventListener("load", n, !1); else if (typeof r.attachEvent != i) dt(r, "onload", n); else if (typeof r.onload == "function") {
            var u = r.onload;
            r.onload = function () {
                u(), n()
            }
        } else r.onload = n
    }

    function ni() {
        vt ? gt() : nt()
    }

    function gt() {
        var o = t.getElementsByTagName("body")[0], e = s(f), r, u;
        e.setAttribute("type", p), r = o.appendChild(e), r ? (u = 0, (function () {
            if (typeof r.GetVariable != i) {
                var t = r.GetVariable("$version");
                t && (t = t.split(" ")[1].split(","), n.pv = [parseInt(t[0], 10), parseInt(t[1], 10), parseInt(t[2], 10)])
            } else if (u < 10) {
                u++, setTimeout(arguments.callee, 10);
                return
            }
            o.removeChild(e), r = null, nt()
        })()) : nt()
    }

    function nt() {
        var y = h.length, r, t, e, c, v;
        if (y > 0) for (r = 0; r < y; r++) {
            var o = h[r].id, s = h[r].callbackFn, f = {success: !1, id: o};
            if (n.pv[0] > 0) {
                t = u(o);
                if (t) if (!g(h[r].swfVersion) || n.wk && n.wk < 312) if (h[r].expressInstall && tt()) {
                    e = {}, e.data = h[r].expressInstall, e.width = t.getAttribute("width") || "0", e.height = t.getAttribute("height") || "0", t.getAttribute("class") && (e.styleclass = t.getAttribute("class")), t.getAttribute("align") && (e.align = t.getAttribute("align"));
                    var p = {}, a = t.getElementsByTagName("param"), w = a.length;
                    for (c = 0; c < w; c++) a[c].getAttribute("name").toLowerCase() != "movie" && (p[a[c].getAttribute("name")] = a[c].getAttribute("value"));
                    et(e, p, o, s)
                } else ui(t), s && s(f); else l(o, !0), s && (f.success = !0, f.ref = it(o), s(f))
            } else l(o, !0), s && (v = it(o), v && typeof v.SetVariable != i && (f.success = !0, f.ref = v), s(f))
        }
    }

    function it(n) {
        var e = null, t = u(n), r;
        return t && t.nodeName == "OBJECT" && (typeof t.SetVariable != i ? e = t : (r = t.getElementsByTagName(f)[0], r && (e = r))), e
    }

    function tt() {
        return !w && g("6.0.65") && (n.win || n.mac) && !(n.wk && n.wk < 312)
    }

    function et(f, e, o, h) {
        var c, v, l, a;
        w = !0, ut = h || null, ct = {
            success: !1,
            id: o
        }, c = u(o), c && (c.nodeName == "OBJECT" ? (y = ft(c), d = null) : (y = c, d = o), f.id = lt, (typeof f.width == i || !/%$/.test(f.width) && parseInt(f.width, 10) < 310) && (f.width = "310"), (typeof f.height == i || !/%$/.test(f.height) && parseInt(f.height, 10) < 137) && (f.height = "137"), t.title = t.title.slice(0, 47) + " - Flash Player Installation", v = n.ie && n.win ? "ActiveX" : "PlugIn", l = "MMredirectURL=" + r.location.toString().replace(/&/g, "%26") + "&MMplayerType=" + v + "&MMdoctitle=" + t.title, typeof e.flashvars != i ? e.flashvars += "&" + l : e.flashvars = l, n.ie && n.win && c.readyState != 4 && (a = s("div"), o += "SWFObjectNew", a.setAttribute("id", o), c.parentNode.insertBefore(a, c), c.style.display = "none", (function () {
            c.readyState == 4 ? c.parentNode.removeChild(c) : setTimeout(arguments.callee, 10)
        })()), rt(f, e, o))
    }

    function ui(t) {
        if (n.ie && n.win && t.readyState != 4) {
            var i = s("div");
            t.parentNode.insertBefore(i, t), i.parentNode.replaceChild(ft(t), i), t.style.display = "none", (function () {
                t.readyState == 4 ? t.parentNode.removeChild(t) : setTimeout(arguments.callee, 10)
            })()
        } else t.parentNode.replaceChild(ft(t), t)
    }

    function ft(t) {
        var e = s("div"), u, i, o, r;
        if (n.win && n.ie) e.innerHTML = t.innerHTML; else {
            u = t.getElementsByTagName(f)[0];
            if (u) {
                i = u.childNodes;
                if (i) for (o = i.length, r = 0; r < o; r++) i[r].nodeType == 1 && i[r].nodeName == "PARAM" || i[r].nodeType == 8 || e.appendChild(i[r].cloneNode(!0))
            }
        }
        return e
    }

    function rt(t, r, e) {
        var v, w = u(e), y, o, b, a, c, h, l;
        if (n.wk && n.wk < 312) return v;
        if (w) {
            typeof t.id == i && (t.id = e);
            if (n.ie && n.win) {
                y = "";
                for (o in t) t[o] != Object.prototype[o] && (o.toLowerCase() == "data" ? r.movie = t[o] : o.toLowerCase() == "styleclass" ? y += ' class="' + t[o] + '"' : o.toLowerCase() != "classid" && (y += " " + o + '="' + t[o] + '"'));
                b = "";
                for (a in r) r[a] != Object.prototype[a] && (b += '<param name="' + a + '" value="' + r[a] + '" />');
                w.outerHTML = '<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"' + y + ">" + b + "</object>", k[k.length] = t.id, v = u(t.id)
            } else {
                c = s(f), c.setAttribute("type", p);
                for (h in t) t[h] != Object.prototype[h] && (h.toLowerCase() == "styleclass" ? c.setAttribute("class", t[h]) : h.toLowerCase() != "classid" && c.setAttribute(h, t[h]));
                for (l in r) r[l] != Object.prototype[l] && l.toLowerCase() != "movie" && ri(c, l, r[l]);
                w.parentNode.replaceChild(c, w), v = c
            }
        }
        return v
    }

    function ri(n, t, i) {
        var r = s("param");
        r.setAttribute("name", t), r.setAttribute("value", i), n.appendChild(r)
    }

    function ht(t) {
        var i = u(t);
        i && i.nodeName == "OBJECT" && (n.ie && n.win ? (i.style.display = "none", (function () {
            i.readyState == 4 ? ii(t) : setTimeout(arguments.callee, 10)
        })()) : i.parentNode.removeChild(i))
    }

    function ii(n) {
        var t = u(n), i;
        if (t) {
            for (i in t) typeof t[i] == "function" && (t[i] = null);
            t.parentNode.removeChild(t)
        }
    }

    function u(n) {
        var i = null;
        try {
            i = t.getElementById(n)
        } catch (r) {
        }
        return i
    }

    function s(n) {
        return t.createElement(n)
    }

    function dt(n, t, i) {
        n.attachEvent(t, i), a[a.length] = [n, t, i]
    }

    function g(t) {
        var r = n.pv, i = t.split(".");
        return i[0] = parseInt(i[0], 10), i[1] = parseInt(i[1], 10) || 0, i[2] = parseInt(i[2], 10) || 0, r[0] > i[0] || r[0] == i[0] && r[1] > i[1] || r[0] == i[0] && r[1] == i[1] && r[2] >= i[2] ? !0 : !1
    }

    function wt(r, u, o, h) {
        var a, l, c;
        if (n.ie && n.mac) return;
        a = t.getElementsByTagName("head")[0];
        if (!a) return;
        l = o && typeof o == "string" ? o : "screen", h && (e = null, ot = null), e && ot == l || (c = s("style"), c.setAttribute("type", "text/css"), c.setAttribute("media", l), e = a.appendChild(c), n.ie && n.win && typeof t.styleSheets != i && t.styleSheets.length > 0 && (e = t.styleSheets[t.styleSheets.length - 1]), ot = l), n.ie && n.win ? e && typeof e.addRule == f && e.addRule(r, u) : e && typeof t.createTextNode != i && e.appendChild(t.createTextNode(r + " {" + u + "}"))
    }

    function l(n, t) {
        if (!bt) return;
        var i = t ? "visible" : "hidden";
        c && u(n) ? u(n).style.visibility = i : wt("#" + n, "visibility:" + i)
    }

    function st(n) {
        var r = /[\\\"<>\.;]/, t = r.exec(n) != null;
        return t && typeof encodeURIComponent != i ? encodeURIComponent(n) : n
    }

    var i = "undefined", f = "object", at = "Shockwave Flash", ti = "ShockwaveFlash.ShockwaveFlash",
        p = "application/x-shockwave-flash", lt = "SWFObjectExprInst", yt = "onreadystatechange", r = window,
        t = document, o = navigator, vt = !1, b = [ni], h = [], k = [], a = [], y, d, ut, ct, c = !1, w = !1, e, ot,
        bt = !0, n = function () {
            var a = typeof t.getElementById != i && typeof t.getElementsByTagName != i && typeof t.createElement != i,
                s = o.userAgent.toLowerCase(), e = o.platform.toLowerCase(), l = e ? /win/.test(e) : /win/.test(s),
                y = e ? /mac/.test(e) : /mac/.test(s),
                v = /webkit/.test(s) ? parseFloat(s.replace(/^.*webkit\/(\d+(\.\d+)?).*$/, "$1")) : !1, h = !1,
                u = [0, 0, 0], n = null, c;
            if (typeof o.plugins != i && typeof o.plugins[at] == f) n = o.plugins[at].description, !n || typeof o.mimeTypes != i && o.mimeTypes[p] && !o.mimeTypes[p].enabledPlugin || (vt = !0, h = !1, n = n.replace(/^.*\s+(\S+\s+\S+$)/, "$1"), u[0] = parseInt(n.replace(/^(.*)\..*$/, "$1"), 10), u[1] = parseInt(n.replace(/^.*\.(.*)\s.*$/, "$1"), 10), u[2] = /[a-zA-Z]/.test(n) ? parseInt(n.replace(/^.*[a-zA-Z]+(.*)$/, "$1"), 10) : 0); else if (typeof r.ActiveXObject != i) try {
                c = new ActiveXObject(ti), c && (n = c.GetVariable("$version"), n && (h = !0, n = n.split(" ")[1].split(","), u = [parseInt(n[0], 10), parseInt(n[1], 10), parseInt(n[2], 10)]))
            } catch (w) {
            }
            return {w3: a, pv: u, wk: v, ie: h, win: l, mac: y}
        }(), ei = function () {
            if (!n.w3) return;
            (typeof t.readyState != i && t.readyState == "complete" || typeof t.readyState == i && (t.getElementsByTagName("body")[0] || t.body)) && v(), c || (typeof t.addEventListener != i && t.addEventListener("DOMContentLoaded", v, !1), n.ie && n.win && (t.attachEvent(yt, function () {
                t.readyState == "complete" && (t.detachEvent(yt, arguments.callee), v())
            }), r == top && (function () {
                if (c) return;
                try {
                    t.documentElement.doScroll("left")
                } catch (n) {
                    setTimeout(arguments.callee, 0);
                    return
                }
                v()
            })()), n.wk && (function () {
                if (c) return;
                if (!/loaded|complete/.test(t.readyState)) {
                    setTimeout(arguments.callee, 0);
                    return
                }
                v()
            })(), pt(v))
        }(), fi = function () {
            n.ie && n.win && window.attachEvent("onunload", function () {
                for (var e = a.length, u, i, r, f, t = 0; t < e; t++) a[t][0].detachEvent(a[t][1], a[t][2]);
                for (u = k.length, i = 0; i < u; i++) ht(k[i]);
                for (r in n) n[r] = null;
                n = null;
                for (f in swfobject) swfobject[f] = null;
                swfobject = null
            })
        }();
    return {
        registerObject: function (t, i, r, u) {
            if (n.w3 && t && i) {
                var f = {};
                f.id = t, f.swfVersion = i, f.expressInstall = r, f.callbackFn = u, h[h.length] = f, l(t, !1)
            } else u && u({success: !1, id: t})
        }, getObjectById: function (t) {
            if (n.w3) return it(t)
        }, embedSWF: function (t, r, u, e, o, s, h, c, a, v) {
            var y = {success: !1, id: r};
            n.w3 && !(n.wk && n.wk < 312) && t && r && u && e && o ? (l(r, !1), kt(function () {
                var n, k, p, b, w, d;
                u += "", e += "", n = {};
                if (a && typeof a === f) for (k in a) n[k] = a[k];
                n.data = t, n.width = u, n.height = e, p = {};
                if (c && typeof c === f) for (b in c) p[b] = c[b];
                if (h && typeof h === f) for (w in h) typeof p.flashvars != i ? p.flashvars += "&" + w + "=" + h[w] : p.flashvars = w + "=" + h[w];
                if (g(o)) d = rt(n, p, r), n.id == r && l(r, !0), y.success = !0, y.ref = d; else {
                    if (s && tt()) {
                        n.data = s, et(n, p, r, v);
                        return
                    }
                    l(r, !0)
                }
                v && v(y)
            })) : v && v(y)
        }, switchOffAutoHideShow: function () {
            bt = !1
        }, ua: n, getFlashPlayerVersion: function () {
            return {major: n.pv[0], minor: n.pv[1], release: n.pv[2]}
        }, hasFlashPlayerVersion: g, createSWF: function (t, i, r) {
            return n.w3 ? rt(t, i, r) : undefined
        }, showExpressInstall: function (t, i, r, u) {
            n.w3 && tt() && et(t, i, r, u)
        }, removeSWF: function (t) {
            n.w3 && ht(t)
        }, createCSS: function (t, i, r, u) {
            n.w3 && wt(t, i, r, u)
        }, addDomLoadEvent: kt, addLoadEvent: pt, getQueryParamValue: function (n) {
            var r = t.location.search || t.location.hash, u, i;
            if (r) {
                /\?/.test(r) && (r = r.split("?")[1]);
                if (n == null) return st(r);
                for (u = r.split("&"), i = 0; i < u.length; i++) if (u[i].substring(0, u[i].indexOf("=")) == n) return st(u[i].substring(u[i].indexOf("=") + 1))
            }
            return ""
        }, expressInstallCallback: function () {
            if (w) {
                var t = u(lt);
                t && y && (t.parentNode.replaceChild(y, t), d && (l(d, !0), n.ie && n.win && (y.style.display = "block")), ut && ut(ct)), w = !1
            }
        }
    }
}();
(function () {
    if ("undefined" == typeof window || window.WebSocket) return;
    var n = window.console;
    n && n.log && n.error || (n = {
        log: function () {
        }, error: function () {
        }
    });
    if (!swfobject.hasFlashPlayerVersion("10.0.0")) {
        n.error("Flash Player >= 10.0.0 is required.");
        return
    }
    location.protocol == "file:" && n.error("WARNING: web-socket-js doesn't work in file:///... URL unless you set Flash Security Settings properly. Open the page via Web server i.e. http://..."), WebSocket = function (n, t, i, r, u) {
        var f = this;
        f.__id = WebSocket.__nextId++, WebSocket.__instances[f.__id] = f, f.readyState = WebSocket.CONNECTING, f.bufferedAmount = 0, f.__events = {}, t ? typeof t == "string" && (t = [t]) : t = [], setTimeout(function () {
            WebSocket.__addTask(function () {
                WebSocket.__flash.create(f.__id, n, t, i || null, r || 0, u || null)
            })
        }, 0)
    }, WebSocket.prototype.send = function (n) {
        if (this.readyState == WebSocket.CONNECTING) throw"INVALID_STATE_ERR: Web Socket connection has not been established";
        var t = WebSocket.__flash.send(this.__id, encodeURIComponent(n));
        return t < 0 ? !0 : (this.bufferedAmount += t, !1)
    }, WebSocket.prototype.close = function () {
        if (this.readyState == WebSocket.CLOSED || this.readyState == WebSocket.CLOSING) return;
        this.readyState = WebSocket.CLOSING, WebSocket.__flash.close(this.__id)
    }, WebSocket.prototype.addEventListener = function (n, t) {
        n in this.__events || (this.__events[n] = []), this.__events[n].push(t)
    }, WebSocket.prototype.removeEventListener = function (n, t) {
        var u, r;
        if (!(n in this.__events)) return;
        for (u = this.__events[n], r = u.length - 1; r >= 0; --r) if (u[r] === t) {
            u.splice(r, 1);
            break
        }
    }, WebSocket.prototype.dispatchEvent = function (n) {
        for (var r = this.__events[n.type] || [], i, t = 0; t < r.length; ++t) r[t](n);
        i = this["on" + n.type], i && i(n)
    }, WebSocket.prototype.__handleEvent = function (n) {
        var t, i;
        "readyState" in n && (this.readyState = n.readyState), "protocol" in n && (this.protocol = n.protocol);
        if (n.type == "open" || n.type == "error") t = this.__createSimpleEvent(n.type); else if (n.type == "close") t = this.__createSimpleEvent("close"); else if (n.type == "message") i = decodeURIComponent(n.message), t = this.__createMessageEvent("message", i); else throw"unknown event type: " + n.type;
        this.dispatchEvent(t)
    }, WebSocket.prototype.__createSimpleEvent = function (n) {
        if (document.createEvent && window.Event) {
            var t = document.createEvent("Event");
            return t.initEvent(n, !1, !1), t
        }
        return {type: n, bubbles: !1, cancelable: !1}
    }, WebSocket.prototype.__createMessageEvent = function (n, t) {
        if (document.createEvent && window.MessageEvent && !window.opera) {
            var i = document.createEvent("MessageEvent");
            return i.initMessageEvent("message", !1, !1, t, null, null, window, null), i
        }
        return {type: n, data: t, bubbles: !1, cancelable: !1}
    }, WebSocket.CONNECTING = 0, WebSocket.OPEN = 1, WebSocket.CLOSING = 2, WebSocket.CLOSED = 3, WebSocket.__flash = null, WebSocket.__instances = {}, WebSocket.__tasks = [], WebSocket.__nextId = 0, WebSocket.loadFlashPolicyFile = function (n) {
        WebSocket.__addTask(function () {
            WebSocket.__flash.loadManualPolicyFile(n)
        })
    }, WebSocket.__initialize = function () {
        var t, i;
        if (WebSocket.__flash) return;
        WebSocket.__swfLocation && (window.WEB_SOCKET_SWF_LOCATION = WebSocket.__swfLocation);
        if (!window.WEB_SOCKET_SWF_LOCATION) {
            n.error("[WebSocket] set WEB_SOCKET_SWF_LOCATION to location of WebSocketMain.swf");
            return
        }
        t = document.createElement("div"), t.id = "webSocketContainer", t.style.position = "absolute", WebSocket.__isFlashLite() ? (t.style.left = "0px", t.style.top = "0px") : (t.style.left = "-100px", t.style.top = "-100px"), i = document.createElement("div"), i.id = "webSocketFlash", t.appendChild(i), document.body.appendChild(t), swfobject.embedSWF(WEB_SOCKET_SWF_LOCATION, "webSocketFlash", "1", "1", "10.0.0", null, null, {
            hasPriority: !0,
            swliveconnect: !0,
            allowScriptAccess: "always"
        }, null, function (t) {
            t.success || n.error("[WebSocket] swfobject.embedSWF failed")
        })
    }, WebSocket.__onFlashInitialized = function () {
        setTimeout(function () {
            WebSocket.__flash = document.getElementById("webSocketFlash"), WebSocket.__flash.setCallerUrl(location.href), WebSocket.__flash.setDebug(!!window.WEB_SOCKET_DEBUG);
            for (var n = 0; n < WebSocket.__tasks.length; ++n) WebSocket.__tasks[n]();
            WebSocket.__tasks = []
        }, 0)
    }, WebSocket.__onFlashEvent = function () {
        return setTimeout(function () {
            var i, t;
            try {
                for (i = WebSocket.__flash.receiveEvents(), t = 0; t < i.length; ++t) WebSocket.__instances[i[t].webSocketId].__handleEvent(i[t])
            } catch (r) {
                n.error(r)
            }
        }, 0), !0
    }, WebSocket.__log = function (t) {
        n.log(decodeURIComponent(t))
    }, WebSocket.__error = function (t) {
        n.error(decodeURIComponent(t))
    }, WebSocket.__addTask = function (n) {
        WebSocket.__flash ? n() : WebSocket.__tasks.push(n)
    }, WebSocket.__isFlashLite = function () {
        if (!window.navigator || !window.navigator.mimeTypes) return !1;
        var n = window.navigator.mimeTypes["application/x-shockwave-flash"];
        return !n || !n.enabledPlugin || !n.enabledPlugin.filename ? !1 : n.enabledPlugin.filename.match(/flashlite/i) ? !0 : !1
    }, window.WEB_SOCKET_DISABLE_AUTO_INITIALIZATION || (window.addEventListener ? window.addEventListener("load", function () {
        WebSocket.__initialize()
    }, !1) : window.attachEvent("onload", function () {
        WebSocket.__initialize()
    }))
})(), (function (n, t, i) {
    function r(n) {
        if (!n) return;
        t.Transport.apply(this, arguments), this.sendBuffer = []
    }

    function u() {
    }

    n.XHR = r, t.util.inherit(r, t.Transport), r.prototype.open = function () {
        return this.socket.setBuffer(!1), this.onOpen(), this.get(), this.setCloseTimeout(), this
    }, r.prototype.payload = function (n) {
        for (var u = [], i = 0, r = n.length; i < r; i++) u.push(t.parser.encodePacket(n[i]));
        this.send(t.parser.encodePayload(u))
    }, r.prototype.send = function (n) {
        return this.post(n), this
    }, r.prototype.post = function (n) {
        function f() {
            this.readyState == 4 && (this.onreadystatechange = u, t.posting = !1, this.status == 200 ? t.socket.setBuffer(!1) : t.onClose())
        }

        function r() {
            this.onload = u, t.socket.setBuffer(!1)
        }

        var t = this;
        this.socket.setBuffer(!0), this.sendXHR = this.request("POST"), i.XDomainRequest && this.sendXHR instanceof XDomainRequest ? this.sendXHR.onload = this.sendXHR.onerror = r : this.sendXHR.onreadystatechange = f, this.sendXHR.send(n)
    }, r.prototype.close = function () {
        return this.onClose(), this
    }, r.prototype.request = function (n) {
        var i = t.util.request(this.socket.isXDomain()), r = t.util.query(this.socket.options.query, "t=" + +new Date);
        i.open(n || "GET", this.prepareUrl() + r, !0);
        if (n == "POST") try {
            i.setRequestHeader ? i.setRequestHeader("Content-type", "text/plain;charset=UTF-8") : i.contentType = "text/plain"
        } catch (u) {
        }
        return i
    }, r.prototype.scheme = function () {
        return this.socket.options.secure ? "https" : "http"
    }, r.check = function (n, i) {
        try {
            if (t.util.request(i)) return !0
        } catch (r) {
        }
        return !1
    }, r.xdomainCheck = function () {
        return r.check(null, !0)
    }
})("undefined" != typeof io ? io.Transport : module.exports, "undefined" != typeof io ? io : module.parent.exports, this), (function (n, t) {
    function i() {
        t.Transport.XHR.apply(this, arguments)
    }

    n.htmlfile = i, t.util.inherit(i, t.Transport.XHR), i.prototype.name = "htmlfile", i.prototype.get = function () {
        var n, r, i;
        this.doc = new ActiveXObject("htmlfile"), this.doc.open(), this.doc.write("<html></html>"), this.doc.close(), this.doc.parentWindow.s = this, n = this.doc.createElement("div"), n.className = "socketio", this.doc.body.appendChild(n), this.iframe = this.doc.createElement("iframe"), n.appendChild(this.iframe), r = this, i = t.util.query(this.socket.options.query, "t=" + +new Date), this.iframe.src = this.prepareUrl() + i;
        t.util.on(window, "unload", function () {
            r.destroy()
        })
    }, i.prototype._ = function (n, t) {
        this.onData(n);
        try {
            var i = t.getElementsByTagName("script")[0];
            i.parentNode.removeChild(i)
        } catch (r) {
        }
    }, i.prototype.destroy = function () {
        if (this.iframe) {
            try {
                this.iframe.src = "about:blank"
            } catch (n) {
            }
            this.doc = null, this.iframe.parentNode.removeChild(this.iframe), this.iframe = null, CollectGarbage()
        }
    }, i.prototype.close = function () {
        return this.destroy(), t.Transport.XHR.prototype.close.call(this)
    }, i.check = function () {
        if (typeof window != "undefined" && "ActiveXObject" in window) try {
            var n = new ActiveXObject("htmlfile");
            return n && t.Transport.XHR.check()
        } catch (i) {
        }
        return !1
    }, i.xdomainCheck = function () {
        return !1
    }, t.transports.push("htmlfile")
})("undefined" != typeof io ? io.Transport : module.exports, "undefined" != typeof io ? io : module.parent.exports), (function (n, t, i) {
    function r() {
        t.Transport.XHR.apply(this, arguments)
    }

    function u() {
    }

    n["xhr-polling"] = r, t.util.inherit(r, t.Transport.XHR), t.util.merge(r, t.Transport.XHR), r.prototype.name = "xhr-polling", r.prototype.open = function () {
        var n = this;
        return t.Transport.XHR.prototype.open.call(n), !1
    }, r.prototype.get = function () {
        function f() {
            if (this.readyState == 4) {
                this.onreadystatechange = u;
                if (this.status == 200) {
                    n.onData(this.responseText);
                    n.get()
                } else n.onClose()
            }
        }

        function r() {
            this.onload = u, this.onerror = u;
            n.onData(this.responseText);
            n.get()
        }

        function t() {
            n.onClose()
        }

        if (!this.open) return;
        var n = this;
        this.xhr = this.request(), i.XDomainRequest && this.xhr instanceof XDomainRequest ? (this.xhr.onload = r, this.xhr.onerror = t) : this.xhr.onreadystatechange = f, this.xhr.send(null)
    }, r.prototype.onClose = function () {
        t.Transport.XHR.prototype.onClose.call(this);
        if (this.xhr) {
            this.xhr.onreadystatechange = this.xhr.onload = this.xhr.onerror = u;
            try {
                this.xhr.abort()
            } catch (n) {
            }
            this.xhr = null
        }
    }, r.prototype.ready = function (n, i) {
        var r = this;
        t.util.defer(function () {
            i.call(r)
        })
    }, t.transports.push("xhr-polling")
})("undefined" != typeof io ? io.Transport : module.exports, "undefined" != typeof io ? io : module.parent.exports, this), (function (n, t, i) {
    function r() {
        t.Transport["xhr-polling"].apply(this, arguments), this.index = t.j.length;
        var i = this;
        t.j.push(function (n) {
            i._(n)
        })
    }

    var u = i.document && "MozAppearance" in i.document.documentElement.style;
    n["jsonp-polling"] = r, t.util.inherit(r, t.Transport["xhr-polling"]), r.prototype.name = "jsonp-polling", r.prototype.post = function (n) {
        function o() {
            e(), r.socket.setBuffer(!1)
        }

        function e() {
            r.iframe && r.form.removeChild(r.iframe);
            try {
                u = document.createElement('<iframe name="' + r.iframeId + '">')
            } catch (n) {
                u = document.createElement("iframe"), u.name = r.iframeId
            }
            u.id = r.iframeId, r.form.appendChild(u), r.iframe = u
        }

        var r = this, h = t.util.query(this.socket.options.query, "t=" + +new Date + "&i=" + this.index);
        if (!this.form) {
            var i = document.createElement("form"), f = document.createElement("textarea"),
                s = this.iframeId = "socketio_iframe_" + this.index, u;
            i.className = "socketio", i.style.position = "absolute", i.style.top = "-1000px", i.style.left = "-1000px", i.target = s, i.method = "POST", i.setAttribute("accept-charset", "utf-8"), f.name = "d", i.appendChild(f), document.body.appendChild(i), this.form = i, this.area = f
        }
        this.form.action = this.prepareUrl() + h, e(), this.area.value = t.JSON.stringify(n);
        try {
            this.form.submit()
        } catch (c) {
        }
        this.iframe.attachEvent ? u.onreadystatechange = function () {
            r.iframe.readyState == "complete" && o()
        } : this.iframe.onload = o, this.socket.setBuffer(!0)
    }, r.prototype.get = function () {
        var r = this, n = document.createElement("script"),
            f = t.util.query(this.socket.options.query, "t=" + +new Date + "&i=" + this.index), i;
        this.script && (this.script.parentNode.removeChild(this.script), this.script = null), n.async = !0, n.src = this.prepareUrl() + f, n.onerror = function () {
            r.onClose()
        }, i = document.getElementsByTagName("script")[0], i.parentNode.insertBefore(n, i), this.script = n, u && setTimeout(function () {
            var n = document.createElement("iframe");
            document.body.appendChild(n), document.body.removeChild(n)
        }, 100)
    }, r.prototype._ = function (n) {
        this.onData(n);
        return this.open && this.get(), this
    }, r.prototype.ready = function (n, i) {
        var r = this;
        if (!u) return i.call(this);
        t.util.load(function () {
            i.call(r)
        })
    }, r.check = function () {
        return "document" in i
    }, r.xdomainCheck = function () {
        return !0
    }, t.transports.push("jsonp-polling")
})("undefined" != typeof io ? io.Transport : module.exports, "undefined" != typeof io ? io : module.parent.exports, this)