/*
 * Copyright 2013-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.springframework.cloud.gateway.handler.predicate;

import org.springframework.http.HttpMethod;
import org.springframework.tuple.Tuple;
import org.springframework.web.server.ServerWebExchange;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author Spencer Gibb
 */
public class MethodRoutePredicateFactory implements RoutePredicateFactory {

	public static final String METHOD_KEY = "method";

	@Override
	public List<String> argNames() {
		return Arrays.asList(METHOD_KEY);
	}

	@Override
	public Predicate<ServerWebExchange> apply(Tuple args) {
		String method = args.getString(METHOD_KEY);
		return exchange -> {
			HttpMethod requestMethod = exchange.getRequest().getMethod();
			// 正则匹配
			return requestMethod.matches(method);
		};
	}
}
