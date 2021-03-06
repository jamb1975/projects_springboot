/*
 * Copyright (c) 2019-2020 VMware, Inc. or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.spring.rabbitmq;


import org.slf4j.Logger;

/**
 * Internal helpers.
 *
 * @since 1.3.0
 */
class Helpers {

    static void safelyExecute(Logger logger, ExceptionRunnable action, String message) {
        try {
            action.run();
        } catch (Exception e) {
            logger.warn(message + ": {}", e.getMessage());
        }
    }

    @FunctionalInterface
    interface ExceptionRunnable {

        void run() throws Exception;

    }

}
